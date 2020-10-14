package com.example.managefield.Interface;


import com.example.managefield.model.Player;

import java.util.List;

public interface LoadListPlayerRequestCallBack {
    void onSuccess(List<Player> listPlayers);

    void onFailure(String message);
}