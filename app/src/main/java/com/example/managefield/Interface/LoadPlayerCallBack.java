package com.example.managefield.Interface;


import com.example.managefield.model.Field;

public interface LoadPlayerCallBack {
    void onSuccess(Field field);

    void onFailure(String message);
}
