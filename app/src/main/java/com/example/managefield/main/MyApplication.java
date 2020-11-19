package com.example.managefield.main;

import android.app.Application;

import com.example.managefield.Session.SessionField;
import com.google.firebase.FirebaseApp;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //init Firebase for app
        SessionField.getInstance().setApplication(this);
        FirebaseApp.initializeApp(this);
    }


}
