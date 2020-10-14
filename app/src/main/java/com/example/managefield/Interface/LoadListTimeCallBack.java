package com.example.managefield.Interface;


import com.example.managefield.model.TimeGame;

import java.util.List;

public interface LoadListTimeCallBack {
    void onSuccess(List<TimeGame> listTimeGames);

    void onFailure(String message);
}
