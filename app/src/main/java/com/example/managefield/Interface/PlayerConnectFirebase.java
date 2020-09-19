package com.example.managefield.Interface;

import com.example.managefield.model.Player;

import java.util.List;

public interface PlayerConnectFirebase {
    void onPlayerLoadedFromFireBase(List<Player> players);
}
