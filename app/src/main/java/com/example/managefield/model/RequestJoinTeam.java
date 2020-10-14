package com.example.managefield.model;

public class RequestJoinTeam {
    private String id ;
    private String idTeam;
    private String idPlayer;
    private boolean isPlayerRequest;

    public RequestJoinTeam() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    public boolean isPlayerRequest() {
        return isPlayerRequest;
    }

    public void setPlayerRequest(boolean playerRequest) {
        isPlayerRequest = playerRequest;
    }
}
