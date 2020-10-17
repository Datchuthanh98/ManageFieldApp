package com.example.managefield.model;

public class Booking {
    private String id;
    private String idField;
    private String idTeamHome;
    private String idTeamAway;
    private String date;
    private String phone;
    private String time;
    private String note;

    public Booking() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public String getIdTeamHome() {
        return idTeamHome;
    }

    public void setIdTeamHome(String idTeamHome) {
        this.idTeamHome = idTeamHome;
    }

    public String getIdTeamAway() {
        return idTeamAway;
    }

    public void setIdTeamAway(String idTeamAway) {
        this.idTeamAway = idTeamAway;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
