package com.example.managefield.Interface;


import com.example.managefield.model.Team;

import java.util.List;

public interface LoadListTeamCallBack {
    void onSuccess(List<Team> listTeams);

    void onFailure(String message);
}
