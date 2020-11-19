package com.example.managefield.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.managefield.Interface.CallBack;
import com.example.managefield.Session.SessionField;
import com.example.managefield.data.enumeration.Result;
import com.example.managefield.data.repository.MatchRepository;
import com.example.managefield.model.Booking;
import com.example.managefield.model.Match;
import com.example.managefield.view.Adapter.RecycleViewAdapterListBookingVertical;
import com.example.managefield.view.Adapter.RecycleViewAdapterListMatchVertical;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListMatchViewModel extends ViewModel{
    private MatchRepository matchRepository = MatchRepository.getInstance();
    private RecycleViewAdapterListMatchVertical adapterListMatchVertical = new RecycleViewAdapterListMatchVertical();
    private MutableLiveData<List<Match>> listBookingFieldLiveData = new MutableLiveData<>();
    private MutableLiveData<Result> resultUpdateScore = new MutableLiveData<>();

    public ListMatchViewModel(){
        getListMatch();
        Log.d("match", "ListMatc 0");
    }

    public void  getListMatch(){
        Log.d("match", "ListMatc 0");
        matchRepository.getListMatch(SessionField.getInstance().getFiledLiveData().getValue().getId(),new CallBack<List<Match>, String>() {
            @Override
            public void onSuccess(List<Match> matchList) {
                Log.d("match", "ListMatc 1");
                if(matchList == null){
                    Log.d("match", "ListMatc 2");
                    listBookingFieldLiveData.setValue(new ArrayList<Match>());
                    adapterListMatchVertical.setListMatch(new ArrayList<Match>());
                    adapterListMatchVertical.notifyDataSetChanged();
                }else{
                    Log.d("match", "ListMatc 3");
                    listBookingFieldLiveData.setValue(matchList);
                    adapterListMatchVertical.setListMatch(matchList);
                    adapterListMatchVertical.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(String message) {

            }
        });
    }


    public RecycleViewAdapterListMatchVertical getAdapterListMatch() {
        return adapterListMatchVertical;
    }

    public void updateScoreMatch(Map<String,Object> map){
        matchRepository.updateScoreMatch(map, new CallBack<String, String>() {
            @Override
            public void onSuccess(String s) {
                resultUpdateScore.setValue(Result.SUCCESS);
            }

            @Override
            public void onFailure(String s) {
                resultUpdateScore.setValue(Result.FAILURE);
            }
        });
    }

    public MutableLiveData<Result> getResultUpdateScore() {
        return resultUpdateScore;
    }
}
