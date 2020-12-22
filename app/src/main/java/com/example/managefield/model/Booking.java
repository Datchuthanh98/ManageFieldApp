package com.example.managefield.model;

public class Booking {
    private String id;
    private Field idField;
    private Team idTeamHome;
    private Team idTeamAway;
    private long date;
    private String phone;
    private String startTime ;
    private String endTime;
    private String position;
    private String cost ;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    private String note;


    public Booking() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Field getIdField() {
        return idField;
    }

    public void setIdField(Field idField) {
        this.idField = idField;
    }

    public Team getIdTeamHome() {
        return idTeamHome;
    }

    public void setIdTeamHome(Team idTeamHome) {
        this.idTeamHome = idTeamHome;
    }

    public Team getIdTeamAway() {
        return idTeamAway;
    }

    public void setIdTeamAway(Team idTeamAway) {
        this.idTeamAway = idTeamAway;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
