package com.example.managefield.Interface;


import com.example.managefield.model.Field;


public interface LoginCallBack {
    void onSuccess(Field field);

    void onFailure(String message);
}
