package com.example.managefield.Interface;


import com.example.managefield.model.Field;


import java.util.List;

public interface LoadListOtherPlayerCallBack {
    void onSuccess(List<Field> fieldList);

    void onFailure(String message);
}
