package com.example.managefield.data.repository;

import com.example.managefield.Interface.AcceptBooking;
import com.example.managefield.Interface.AddBookingField;
import com.example.managefield.Interface.DeclineBooking;
import com.example.managefield.Interface.LoadListBookingCallBack;
import com.example.managefield.Interface.LoadListMatchCallBack;
import com.example.managefield.data.datasource.MatchDataSource;
import com.example.managefield.model.Booking;

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

    public void getListBooking(LoadListBookingCallBack loadListBookingCallBack){
        matchDataSource.loadListBooking(loadListBookingCallBack);
    }

    public void getListMatch(LoadListMatchCallBack loadListMatchCallBack){
        matchDataSource.loadListMatch(loadListMatchCallBack);
    }

    public  void acceptBooking(String id, AcceptBooking acceptBooking){
         matchDataSource.acceptBooking(id,acceptBooking);
    }

    public  void declineBooking(String  id, DeclineBooking declineBooking){
        matchDataSource.declineBooking(id, declineBooking);
    }
}
