package com.example.managefield.view.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managefield.databinding.ItemBookingVerticalBinding;
import com.example.managefield.model.Booking;
import com.example.managefield.viewModel.ListBookingViewModel;
//import com.example.managefield.view.Fragment.FragmentMainProfileMatch;

import java.util.ArrayList;
import java.util.List;


public class RecycleViewAdapterListBookingVertical extends RecyclerView.Adapter<RecycleViewAdapterListBookingVertical.MyViewHolder> {
    private FragmentManager fm;
    private List<Booking> bookingList = new ArrayList<>();
    private ListBookingViewModel listBookingViewModel;
    public RecycleViewAdapterListBookingVertical() {
    }

    public RecycleViewAdapterListBookingVertical(FragmentManager fm) {
        this.fm = fm;
    }

    public void setListBookingViewModel(ListBookingViewModel listBookingViewModel) {
        this.listBookingViewModel = listBookingViewModel;
    }

    public void setFm(FragmentManager fm) {
        this.fm = fm;
    }

    public  void  setListBooking(List<Booking> listBooking){
        this.bookingList = listBooking;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemBookingVerticalBinding binding = ItemBookingVerticalBinding.inflate(inflater, parent, false);
        return new MyViewHolder(binding);
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        final ItemBookingVerticalBinding binding;
        public MyViewHolder(ItemBookingVerticalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ActivityMainField activityHome = (ActivityMainField) holder.itemView.getContext();
//                activityHome.addFragment(new FragmentMainProfileMatch());
            }
        });

        holder.binding.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBookingViewModel.acceptBooking(bookingList.get(position).getId());
            }
        });


        holder.binding.btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBookingViewModel.declineBooking(bookingList.get(position).getId());
            }
        });

        holder.binding.setBooking(bookingList.get(position));
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }
}

