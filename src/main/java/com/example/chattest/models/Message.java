package com.example.chattest.models;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message {

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private MessageType messageType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User from;

    @Column(nullable = false)
    private String text;

    public Message() {
    }

    public Message(User from, String text) {
        this.from = from;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
