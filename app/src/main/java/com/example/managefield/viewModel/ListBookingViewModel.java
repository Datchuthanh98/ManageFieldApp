package com.example.managefield.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.managefield.Interface.CallBack;
import com.example.managefield.Session.SessionStateData;
import com.example.managefield.data.enumeration.DataState;
import com.example.managefield.data.enumeration.LoadingState;
import com.example.managefield.data.enumeration.Status;
import com.example.managefield.data.repository.MatchRepository;
import com.example.managefield.model.Booking;
import com.example.managefield.view.Adapter.RecycleViewAdapterListBookingVertical;

import java.util.ArrayList;
import java.util.List;

public class ListBookingViewModel extends ViewModel{
    private MatchRepository matchRepository = MatchRepository.getInstance();
    private RecycleViewAdapterListBookingVertical adapterListBooking = new RecycleViewAdapterListBookingVertical();
    private MutableLiveData<List<Booking>> listBookingFieldLiveData = new MutableLiveData<>();
    private MutableLiveData<LoadingState> teamLoadState = new MutableLiveData<>(LoadingState.INIT);
    private MutableLiveData<LoadingState> executeState = new MutableLiveData<>();
    private MutableLiveData<Status> statusData = new MutableLiveData<>(Status.NO_DATA);

    public ListBookingViewModel(){
//        getListBooking();
        adapterListBooking.setListBookingViewModel(this);
    }

    public void  getListBooking(String idField){
        teamLoadState.setValue(LoadingState.INIT);
        matchRepository.getListBooking(idField,new CallBack<List<Booking>, String>() {
            @Override
            public void onSuccess(List<Booking> bookingList) {
                teamLoadState.setValue(LoadingState.LOADED);
                if(bookingList.size() == 0){
                    statusData.setValue(Status.NO_DATA);
                    listBookingFieldLiveData.setValue(new ArrayList<Booking>());
                    adapterListBooking.setListBooking(new ArrayList<Booking>());
                    adapterListBooking.notifyDataSetChanged();
                }else{
                    statusData.setValue(Status.EXIST_DATA);
                    listBookingFieldLiveData.setValue(bookingList);
                    adapterListBooking.setListBooking(bookingList);
                    adapterListBooking.notifyDataSetChanged();

                }


            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    public  void acceptBooking(Booking booking) {
        executeState.setValue(LoadingState.INIT);

        matchRepository.acceptBooking(booking, new CallBack<String, String>() {
            @Override
            public void onSuccess(String s) {
                executeState.setValue(LoadingState.LOADED);
                SessionStateData.getInstance().setDatalistBooking(DataState.NEW);
                SessionStateData.getInstance().setDatalistMatch(DataState.NEW);

            }

            @Override
            public void onFailure(String s) {

            }

            ;
        });
    }

    public  void declineBooking(Booking booking){
        executeState.setValue(LoadingState.INIT);
        matchRepository.declineBooking(booking, new CallBack<String, String>() {
            @Override
            public void onSuccess(String s) {
                executeState.setValue(LoadingState.LOADED);
                SessionStateData.getInstance().setDatalistBooking(DataState.NEW);
                SessionStateData.getInstance().setDatalistMatch(DataState.NEW);
            }

            @Override
            public void onFailure(String s) {

            }

        });

    }



    public RecycleViewAdapterListBookingVertical getAdapterListBooking() {
        return adapterListBooking;
    }

    public MutableLiveData<LoadingState> getTeamLoadState() {
        return teamLoadState;
    }

    public void setTeamLoadState(MutableLiveData<LoadingState> teamLoadState) {
        this.teamLoadState = teamLoadState;
    }

    public MutableLiveData<LoadingState> getExecuteState() {
        return executeState;
    }

    public void setExecuteState(MutableLiveData<LoadingState> executeState) {
        this.executeState = executeState;
    }

    public MutableLiveData<Status> getStatusData() {
        return statusData;
    }

    public void setStatusData(MutableLiveData<Status> statusData) {
        this.statusData = statusData;
    }
}
