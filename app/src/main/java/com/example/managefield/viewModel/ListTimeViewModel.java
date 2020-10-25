package com.example.managefield.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.managefield.Interface.CallBack;
import com.example.managefield.data.enumeration.DataState;
import com.example.managefield.data.enumeration.Result;
import com.example.managefield.data.repository.MatchRepository;
import com.example.managefield.data.repository.TimeGameRepository;
import com.example.managefield.model.Booking;
import com.example.managefield.model.TimeGame;
import com.example.managefield.view.Adapter.RecycleViewAdapterListBookingVertical;
import com.example.managefield.view.Adapter.RecycleViewAdapterListTimeVertical;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListTimeViewModel extends ViewModel{
    private TimeGameRepository timeGameRepository = TimeGameRepository.getInstance();
    private RecycleViewAdapterListTimeVertical adapterListTime = new RecycleViewAdapterListTimeVertical();
    private MutableLiveData<List<TimeGame>> listTimeLiveData = new MutableLiveData<>();
    private String idTeam = SessionField.getInstance().getPlayerLiveData().getValue().getId();

    public ListTimeViewModel(){
        getListTime();
        adapterListTime.setListTimeViewModel(this);
    }

    public void  getListTime(){
        timeGameRepository.getListTime( idTeam,new CallBack<List<TimeGame>, String>() {
            @Override
            public void onSuccess(List<TimeGame> timeGames) {
                if(timeGames == null){
                    listTimeLiveData.setValue(new ArrayList<TimeGame>());
                    adapterListTime.setListTime(new ArrayList<TimeGame>());
                    adapterListTime.notifyDataSetChanged();
                }else{
                    listTimeLiveData.setValue(timeGames);
                    adapterListTime.setListTime(timeGames);
                    adapterListTime.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    public void createTeam(Map<String,Object> data){
        timeGameRepository.creatTeam(data, new CallBack<String, String>() {
            @Override
            public void onSuccess(String s) {
            SessionStateData.getInstance().setDatalistTime(DataState.NEW);
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }


    public RecycleViewAdapterListTimeVertical getAdapterListTime() {
        return adapterListTime;
    }

}
