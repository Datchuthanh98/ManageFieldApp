package com.example.managefield.Interface;


import com.example.managefield.model.Player;

public interface LoginCallBack {
    void onSuccess(Player player);

    void onFailure(String message);
}
