package com.example.chattest.controllers;

import com.example.chattest.models.Message;
import com.example.chattest.models.OutputMessage;
import com.example.chattest.models.User;
import com.example.chattest.repositories.UserRepository;
import com.example.chattest.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.format;

@Controller
public class ChatController {

    private final UserRepository userDao;
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    public ChatController(UserRepository userDao) {
        this.userDao = userDao;
    }


    @MessageMapping("/chat/{roomId}/send")
    public void sendMessage(@DestinationVariable Long roomId, @Payload Message chatMessage) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("inside sendMessage");
        System.out.println(mapper.writeValueAsString(chatMessage));
        messagingTemplate.convertAndSend(format("/secured/room/%s", roomId), chatMessage);
    }

    @MessageMapping("/chat/{roomId}/add-user")
    public void addUser(@DestinationVariable Long roomId, @Payload Message chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        User user = userDao.findById(Utils.currentUserId()).get();
        chatMessage.setSender(user);
        Long currentRoomId = (Long) headerAccessor.getSessionAttributes().put("room_id", roomId);
        if(currentRoomId != null) {
            Message leaveMessage = new Message();
            leaveMessage.setMessageType(Message.MessageType.LEAVE);
            leaveMessage.setSender(user);
            messagingTemplate.convertAndSend(format("/secured/room/%s", currentRoomId), leaveMessage);
        }
        headerAccessor.getSessionAttributes().put("name", chatMessage.getSender());
        messagingTemplate.convertAndSend(format("/secured/room/%s", roomId), chatMessage);
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(Message message) throws Exception {
        Timestamp time = new Timestamp(new Date().getTime());
        return new OutputMessage(message.getSender(), message.getText(), time);
    }
}
