package com.example.managefield.model;

import java.util.Map;

public class Player {
    private String id;
    private String email;
    private String phone;
    private String password;
    private String address;
    private String name;
    private String urlCover;
    private String urlAvatar;
    private String birthday;
    private int height;
    private int weight;
    private String introduce;
    private String position;
    private String level;
    private String foot;


    public Player() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlCover() {
        return urlCover;
    }

    public void setUrlCover(String urlCover) {
        this.urlCover = urlCover;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFoot() {
        return foot;
    }

    public void setFoot(String foot) {
        this.foot = foot;
    }

    public  void setInforBasic(Map<String, Object> inforBasic){
        this.name = inforBasic.get("name").toString() ;
        this.phone =  inforBasic.get("phone").toString() ;
        this.email =  inforBasic.get("email").toString() ;
        this.address =  inforBasic.get("address").toString() ;
    }

    public void setInforPlayer(Map<String, Object> inforPlayer) {
        this.birthday = inforPlayer.get("birthday").toString();
        this.height =  Integer.parseInt(inforPlayer.get("height").toString());
        this.weight = Integer.parseInt(inforPlayer.get("weight").toString());
        this.position = inforPlayer.get("position").toString();
        this.level = inforPlayer.get("level").toString();
        this.foot = inforPlayer.get("foot").toString();
    }





}
