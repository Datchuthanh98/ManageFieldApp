package com.example.managefield.data.repository;


import com.example.managefield.Interface.CallBack;
import com.example.managefield.data.datasource.MatchDataSource;
import com.example.managefield.model.Booking;
import com.example.managefield.model.Match;

import java.util.List;
import java.util.Map;

public class MatchRepository {

    private static volatile MatchRepository instance;

    private MatchDataSource matchDataSource = new MatchDataSource();

    private MatchRepository(){}

    public static MatchRepository getInstance(){
        if (instance == null){
            instance = new MatchRepository();
        }
        return instance;
    }

    public void getListBooking(String idField,CallBack<List<Booking>,String> loadListBookingCallBack){
        matchDataSource.loadListBooking(idField,loadListBookingCallBack);
    }

    public void getListMatch(CallBack<List<Match>,String> loadListMatchCallBack){
        matchDataSource.loadListMatch(loadListMatchCallBack);
    }

    public  void acceptBooking(String id, CallBack<String,String> acceptBooking){
         matchDataSource.acceptBooking(id,acceptBooking);
    }

    public  void declineBooking(String  id, CallBack<String,String> declineBooking){
        matchDataSource.declineBooking(id, declineBooking);
    }

    public void updateScoreMatch(Map<String,Object> map ,CallBack<String,String> updateScore){
         matchDataSource.updateScoreMatch(map,updateScore);
    }
}
