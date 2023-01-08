package com.example.chattest.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "output_messages")
public class OutputMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User from;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Timestamp time;

    public OutputMessage() {
    }

    public OutputMessage(User from, String text, Timestamp time) {
        this.from = from;
        this.text = text;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
