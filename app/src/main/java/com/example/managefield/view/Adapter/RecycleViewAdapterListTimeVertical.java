package com.example.managefield.view.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managefield.databinding.ItemBookingVerticalBinding;
import com.example.managefield.databinding.ItemTimeHorizontalBinding;
import com.example.managefield.main.ActivityMain;
import com.example.managefield.model.Booking;
import com.example.managefield.model.TimeGame;
import com.example.managefield.view.Fragment.FragmentUpdateScore;
import com.example.managefield.viewModel.ListBookingViewModel;
import com.example.managefield.viewModel.ListTimeViewModel;
import com.example.managefield.viewModel.UpdateScoreViewModel;

import java.util.ArrayList;
import java.util.List;

//import com.example.managefield.view.Fragment.FragmentMainProfileMatch;


public class RecycleViewAdapterListTimeVertical extends RecyclerView.Adapter<RecycleViewAdapterListTimeVertical.MyViewHolder> {
    private FragmentManager fm;
    private List<TimeGame> timeGameList = new ArrayList<>();
    private ListTimeViewModel listTimeViewModel;
    public RecycleViewAdapterListTimeVertical() {
    }

    public RecycleViewAdapterListTimeVertical(FragmentManager fm) {
        this.fm = fm;
    }

    public void setListTimeViewModel(ListTimeViewModel listTimeViewModel) {
        this.listTimeViewModel = listTimeViewModel;
    }

    public void setFm(FragmentManager fm) {
        this.fm = fm;
    }

    public  void  setListTime(List<TimeGame> listTimeGame){
        this.timeGameList = listTimeGame;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemTimeHorizontalBinding binding = ItemTimeHorizontalBinding.inflate(inflater, parent, false);
        return new MyViewHolder(binding);
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        final ItemTimeHorizontalBinding binding;
        public MyViewHolder(ItemTimeHorizontalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        holder.binding.btnAccept.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listBookingViewModel.acceptBooking(bookingList.get(position).getId());
//            }
//        });
//
//
//        holder.binding.btnDecline.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listBookingViewModel.declineBooking(bookingList.get(position).getId());
//            }
//        });

        holder.binding.setTimeGame(timeGameList.get(position));
    }

    @Override
    public int getItemCount() {
        return timeGameList.size();
    }
}

