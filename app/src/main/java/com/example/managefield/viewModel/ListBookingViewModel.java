package com.example.managefield.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.managefield.Interface.CallBack;
import com.example.managefield.Session.SessionStateData;
import com.example.managefield.data.enumeration.DataState;
import com.example.managefield.data.repository.MatchRepository;
import com.example.managefield.model.Booking;
import com.example.managefield.view.Adapter.RecycleViewAdapterListBookingVertical;

import java.util.ArrayList;
import java.util.List;

public class ListBookingViewModel extends ViewModel{
    private MatchRepository matchRepository = MatchRepository.getInstance();
    private RecycleViewAdapterListBookingVertical adapterListBooking = new RecycleViewAdapterListBookingVertical();
    private MutableLiveData<List<Booking>> listBookingFieldLiveData = new MutableLiveData<>();

    public ListBookingViewModel(){
//        getListBooking();
        adapterListBooking.setListBookingViewModel(this);
    }

    public void  getListBooking(String idField){
        matchRepository.getListBooking(idField,new CallBack<List<Booking>, String>() {
            @Override
            public void onSuccess(List<Booking> bookingList) {
                if(bookingList == null){
                    listBookingFieldLiveData.setValue(new ArrayList<Booking>());
                    adapterListBooking.setListBooking(new ArrayList<Booking>());
                    adapterListBooking.notifyDataSetChanged();
                }else{
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

    public  void acceptBooking(String id) {
        matchRepository.acceptBooking(id, new CallBack<String, String>() {
            @Override
            public void onSuccess(String s) {
                SessionStateData.getInstance().setDatalistBooking(DataState.NEW);
                SessionStateData.getInstance().setDatalistMatch(DataState.NEW);
            }

            @Override
            public void onFailure(String s) {

            }

            ;
        });
    }

    public  void declineBooking(String  id){
        matchRepository.declineBooking(id, new CallBack<String, String>() {
            @Override
            public void onSuccess(String s) {
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

}
