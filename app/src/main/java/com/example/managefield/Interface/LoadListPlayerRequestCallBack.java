package com.example.managefield.Interface;


import com.example.managefield.model.Field;


import java.util.List;

public interface LoadListPlayerRequestCallBack {
    void onSuccess(List<Field> listPlayers);

    void onFailure(String message);
}
