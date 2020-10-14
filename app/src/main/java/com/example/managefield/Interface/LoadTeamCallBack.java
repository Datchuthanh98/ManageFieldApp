package com.example.managefield.Interface;


import com.example.managefield.model.Team;

public interface LoadTeamCallBack {
    void onSuccess(Team team);

    void onFailure(String message);
}
