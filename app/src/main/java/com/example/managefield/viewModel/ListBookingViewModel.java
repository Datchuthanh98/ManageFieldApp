package com.example.managefield.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.managefield.Interface.AcceptBooking;
import com.example.managefield.Interface.DeclineBooking;
import com.example.managefield.Interface.LoadListBookingCallBack;
import com.example.managefield.data.enumeration.Result;
import com.example.managefield.data.repository.MatchRepository;
import com.example.managefield.model.Booking;
import com.example.managefield.view.Adapter.RecycleViewAdapterListBookingVertical;

import java.util.ArrayList;
import java.util.List;

public class ListBookingViewModel extends ViewModel{
    private MatchRepository matchRepository = MatchRepository.getInstance();
    private RecycleViewAdapterListBookingVertical adapterListBooking = new RecycleViewAdapterListBookingVertical();
    private MutableLiveData<List<Booking>> listBookingFieldLiveData = new MutableLiveData<>();
    private MutableLiveData<Result> resultLiveData = new MutableLiveData<>(null);

    public ListBookingViewModel(){
        getListBooking();
        adapterListBooking.setListBookingViewModel(this);
    }

    public void  getListBooking(){
        matchRepository.getListBooking(new LoadListBookingCallBack() {
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
        matchRepository.acceptBooking(id, new AcceptBooking() {
            @Override
            public void onSuccess() {
                resultLiveData.setValue(Result.SUCCESS);
            }

            @Override
            public void onFailure() {
                resultLiveData.setValue(Result.FAILURE);
            }
        });
    }

    public  void declineBooking(String  id){
        matchRepository.declineBooking(id, new DeclineBooking() {
            @Override
            public void onSuccess() {
                resultLiveData.setValue(Result.SUCCESS);
            }

            @Override
            public void onFailure() {
                resultLiveData.setValue(Result.FAILURE);
            }
        });

    }



    public LiveData<Result> getResultLiveData() {
        return resultLiveData;
    }

    public RecycleViewAdapterListBookingVertical getAdapterListBooking() {
        return adapterListBooking;
    }

}
