package com.example.managefield.data.repository;


import com.example.managefield.Interface.CallBack;
import com.example.managefield.data.datasource.MatchDataSource;
import com.example.managefield.data.datasource.TimeDataSource;
import com.example.managefield.model.Booking;
import com.example.managefield.model.Match;
import com.example.managefield.model.TimeGame;

import java.util.List;
import java.util.Map;

public class TimeGameRepository {

    private static volatile TimeGameRepository instance;

    private TimeDataSource timeDataSource = new TimeDataSource();

    private TimeGameRepository(){}

    public static TimeGameRepository getInstance(){
        if (instance == null){
            instance = new TimeGameRepository();
        }
        return instance;
    }

    public void getListTime(String idTeam ,CallBack<List<TimeGame>, String> loadListBookingCallBack){
        timeDataSource.loadListTime(idTeam,loadListBookingCallBack);
    }

    public void creatTeam(String idTeam,Map<String,Object> map, CallBack<String ,String> checkteam) {
        timeDataSource.createTeam( idTeam,map,checkteam);
    }

    public void updateTime(Map<String,Object> map, CallBack<String ,String> checkteam) {
        timeDataSource.updateTime(map,checkteam);
    }


}
