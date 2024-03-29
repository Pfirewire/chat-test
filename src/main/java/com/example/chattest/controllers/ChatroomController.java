package com.example.chattest.controllers;

import com.example.chattest.models.ChatRoom;
import com.example.chattest.repositories.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatroomController {

    private final ChatRoomRepository chatRoomDao;

    public ChatroomController(ChatRoomRepository chatRoomDao) {
        this.chatRoomDao = chatRoomDao;
    }

    @GetMapping("/room/create")
    public String createChatRoom(@RequestParam(name = "chatroom-name") String name, Model model) {
        ChatRoom newRoom = new ChatRoom(name);
        chatRoomDao.save(newRoom);
        model.addAttribute("room", newRoom);
        return "room/chat";
    }

    @GetMapping("/room/join")
    public String joinChatRoom(@RequestParam(name = "chatroom-name") String name, Model model) {
        ChatRoom room = chatRoomDao.findByName(name);
        model.addAttribute("room", room);
        return "room/chat";
    }
}
