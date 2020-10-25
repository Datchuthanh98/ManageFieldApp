package com.example.managefield.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Team implements Serializable {
    private String id;
    private String name;
    private String urlAvatar;
    private String urlCover;
    private String email;
    private String phone;
    private String address;
    private String introduce;
    private String level;
    private Float rating;
    private Float reputaion;
    private Player captain;
    private List<Player> listPlayers = new ArrayList<>();

    public Team() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public String getUrlCover() {
        return urlCover;
    }

    public void setUrlCover(String urlCover) {
        this.urlCover = urlCover;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Float getReputaion() {
        return reputaion;
    }

    public void setReputaion(Float reputaion) {
        this.reputaion = reputaion;
    }

    public Player getCaptain() {
        return captain;
    }

    public void setCaptain(Player captain) {
        this.captain = captain;
    }

    public List<Player> getListPlayers() {
        return listPlayers;
    }

    public void setListPlayers(List<Player> listPlayers) {
        this.listPlayers = listPlayers;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public  void setInforBasic(Map<String, Object> inforBasic){
        this.name = inforBasic.get("name").toString() ;
        this.phone =  inforBasic.get("phone").toString() ;
        this.email =  inforBasic.get("email").toString() ;
        this.address =  inforBasic.get("address").toString() ;
    }


}
