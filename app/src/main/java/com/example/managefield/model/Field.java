package com.example.managefield.model;

import java.util.Map;

public class Field {
    private  String id ;
    private  String address;
    private  String name;
    private  String phone;
    private  String email;
    private  String longitude;
    private  String latitude;
    private  String urlAvatar;
    private  String urlCover;
    private  String introduce;

    public Field() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public  void setInforBasic(Map<String, Object> inforBasic){
        this.name = inforBasic.get("name").toString() ;
        this.phone =  inforBasic.get("phone").toString() ;
        this.email =  inforBasic.get("email").toString() ;
        this.address =  inforBasic.get("address").toString() ;
    }






}
