package com.example.chattest.models;

import javax.persistence.*;

public class Message {
    private Long id;
    private String from;
    private String text;

    public Message() {
    }

    public Message(String from, String text) {
        this.from = from;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
