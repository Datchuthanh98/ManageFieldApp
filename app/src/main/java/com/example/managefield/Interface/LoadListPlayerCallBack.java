package com.example.managefield.Interface;


import com.example.managefield.model.Field;

import java.util.List;

public interface LoadListPlayerCallBack {
    void onSuccess(List<Field> fields);

    void onFailure(String message);
}
