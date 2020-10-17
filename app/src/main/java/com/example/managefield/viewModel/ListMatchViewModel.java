package com.example.managefield.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.managefield.Interface.AcceptBooking;
import com.example.managefield.Interface.DeclineBooking;
import com.example.managefield.Interface.LoadListBookingCallBack;
import com.example.managefield.Interface.LoadListMatchCallBack;
import com.example.managefield.data.enumeration.Result;
import com.example.managefield.data.repository.MatchRepository;
import com.example.managefield.model.Booking;
import com.example.managefield.model.Match;
import com.example.managefield.view.Adapter.RecycleViewAdapterListBookingVertical;
import com.example.managefield.view.Adapter.RecycleViewAdapterListMatchVertical;

import java.util.ArrayList;
import java.util.List;

public class ListMatchViewModel extends ViewModel{
    private MatchRepository matchRepository = MatchRepository.getInstance();
    private RecycleViewAdapterListMatchVertical adapterListMatchVertical = new RecycleViewAdapterListMatchVertical();
    private MutableLiveData<List<Match>> listBookingFieldLiveData = new MutableLiveData<>();
    private MutableLiveData<Result> resultLiveData = new MutableLiveData<>(null);

    public ListMatchViewModel(){
        getListMatch();
        adapterListMatchVertical.setListBookingViewModel(this);
    }

    public void  getListMatch(){
        matchRepository.getListMatch(new LoadListMatchCallBack() {
            @Override
            public void onSuccess(List<Match> matchList) {
                if(matchList == null){
                    listBookingFieldLiveData.setValue(new ArrayList<Match>());
                    adapterListMatchVertical.setListMatch(new ArrayList<Match>());
                    adapterListMatchVertical.notifyDataSetChanged();
                }else{
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



    public LiveData<Result> getResultLiveData() {
        return resultLiveData;
    }

    public RecycleViewAdapterListMatchVertical getAdapterListMatch() {
        return adapterListMatchVertical;
    }

}
