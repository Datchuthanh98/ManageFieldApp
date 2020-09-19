package com.example.managefield.model;

import java.io.Serializable;
import java.util.List;

public class Player extends User implements Serializable {
    private  int height ;
    private  int weight ;
    private  String introduce;
    private List<String> position;
    private  String idClub;

     Player() {
        super();
    }


}
