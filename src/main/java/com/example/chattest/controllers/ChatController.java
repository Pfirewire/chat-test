package com.example.chattest.controllers;

import com.example.chattest.models.Message;
import com.example.chattest.models.OutputMessage;
import com.example.chattest.repositories.UserRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChatController {

    private final UserRepository userDao;

    public ChatController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(Message message) throws Exception {
        Timestamp time = new Timestamp(new Date().getTime());
        return new OutputMessage(userDao.findByUsername(message.getFrom()), message.getText(), time);
    }
}
