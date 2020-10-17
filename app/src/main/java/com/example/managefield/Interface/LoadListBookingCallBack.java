package com.example.managefield.Interface;


import com.example.managefield.model.Booking;

import java.util.List;

public interface LoadListBookingCallBack {
    void onSuccess(List<Booking> bookingList);

    void onFailure(String message);
}
