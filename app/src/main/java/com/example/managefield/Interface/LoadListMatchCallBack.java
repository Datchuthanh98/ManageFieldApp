package com.example.managefield.Interface;


import com.example.managefield.model.Booking;
import com.example.managefield.model.Field;
import com.example.managefield.model.Match;

import java.util.List;

public interface LoadListMatchCallBack {
    void onSuccess(List<Match> mathList);

    void onFailure(String message);
}
