package com.example.managefield.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public class UpdateScoreViewModel  extends ViewModel {
    MutableLiveData<Map<String,Object>> scoreList = new MutableLiveData<>();

    public MutableLiveData<Map<String, Object>> getScoreList() {
        return scoreList;
    }

    public void setScoreList(Map<String, Object> scoreList) {
        this.scoreList.setValue(scoreList);
    }
}
