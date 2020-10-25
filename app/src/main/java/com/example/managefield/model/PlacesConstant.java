package com.example.managefield.model;

import androidx.lifecycle.MutableLiveData;

public class PlacesConstant {
    public static double longitude = 0;
    public static double latitude = 0;
    public static MutableLiveData<Integer> locationChange = new MutableLiveData<>(null);
    public static int radius = 500;
}
