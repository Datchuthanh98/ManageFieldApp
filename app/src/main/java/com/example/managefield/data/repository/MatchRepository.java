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

    public void getListMatch(String idField,CallBack<List<Match>,String> loadListMatchCallBack){
        matchDataSource.loadListMatch(idField,loadListMatchCallBack);
    }

    public  void acceptBooking(Booking booking, CallBack<String,String> acceptBooking){
         matchDataSource.acceptBooking(booking,acceptBooking);
    }

    public  void declineBooking(Booking booking, CallBack<String,String> declineBooking){
        matchDataSource.declineBooking(booking, declineBooking);
    }

    public void updateScoreMatch(Map<String,Object> map ,CallBack<String,String> updateScore){
         matchDataSource.updateScoreMatch(map,updateScore);
    }
}
