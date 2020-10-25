package com.example.managefield.model;

public class Booking {
    private String id;
    private Field idField;
    private Team idTeamHome;
    private Team idTeamAway;
    private String date;
    private String phone;
    private TimeGame idTimeGame;
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

    public TimeGame getIdTimeGame() {
        return idTimeGame;
    }

    public void setIdTimeGame(TimeGame idTimeGame) {
        this.idTimeGame = idTimeGame;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
