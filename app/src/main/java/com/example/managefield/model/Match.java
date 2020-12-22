package com.example.managefield.model;

import java.util.List;

public class Match {
    private  String id;
    private  Booking idBooking;
    private  String scoreHome;
    private  String scoreAway;
    private  long date;
    private  Boolean active ;
    private List<Team> listQueueTeam;

    public List<Team> getListQueueTeam() {
        return listQueueTeam;
    }

    public void setListQueueTeam(List<Team> listQueueTeam) {
        this.listQueueTeam = listQueueTeam;
    }

    public Match() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Booking getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Booking idBooking) {
        this.idBooking = idBooking;
    }

    public String getScoreHome() {
        return scoreHome;
    }

    public void setScoreHome(String scoreHome) {
        this.scoreHome = scoreHome;
    }

    public String getScoreAway() {
        return scoreAway;
    }

    public void setScoreAway(String scoreAway) {
        this.scoreAway = scoreAway;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
