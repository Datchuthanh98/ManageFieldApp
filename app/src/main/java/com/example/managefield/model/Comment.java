package com.example.managefield.model;

import com.google.firebase.Timestamp;

public class Comment {
    private String id ;
    private Player player;
    private  String comment ;
    private Timestamp timeComment;

    public Comment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getTimeComment() {
        return timeComment;
    }

    public void setTimeComment(Timestamp timeComment) {
        this.timeComment = timeComment;
    }
}
