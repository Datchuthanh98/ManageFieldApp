package com.example.managefield.Interface;

public interface CallBack<T1, T2> {
    void onSuccess(T1 t1);
    void onFailure(T2 t2);
}
