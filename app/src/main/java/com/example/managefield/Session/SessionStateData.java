package com.example.managefield.Session;

import androidx.lifecycle.MutableLiveData;

import com.example.managefield.data.enumeration.DataState;

public class SessionStateData {
      private MutableLiveData<DataState> datalistMatch = new MutableLiveData<>(DataState.NOW);
      private MutableLiveData<DataState> datalistBooking = new MutableLiveData<>(DataState.NOW);
    private MutableLiveData<DataState> datalistTime = new MutableLiveData<>(DataState.NOW);
      private static SessionStateData instance;
    public static SessionStateData getInstance() {
        if (instance == null) {
            instance = new SessionStateData();
        }
        return instance;
    }


    public MutableLiveData<DataState> getDatalistMatch() {
        return datalistMatch;
    }

    public void setDatalistMatch(DataState datalistField) {
        this.datalistMatch.setValue(datalistField);
    }

    public MutableLiveData<DataState> getDatalistBooking() {
        return datalistBooking;
    }

    public void setDatalistBooking(DataState datalistBooking) {
        this.datalistBooking.setValue(datalistBooking);
    }

    public MutableLiveData<DataState> getDatalistTime() {
        return datalistTime;
    }

    public void setDatalistTime(DataState datalistTime) {
        this.datalistTime.setValue(datalistTime);
    }
}
