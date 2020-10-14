package com.example.managefield.view.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managefield.databinding.ItemMatchVerticalBinding;


public class RecycleViewAdapterListBookingVertical extends RecyclerView.Adapter<RecycleViewAdapterListBookingVertical.MyViewHolder> {
    private FragmentManager fm;
    public RecycleViewAdapterListBookingVertical(FragmentManager fm) {
        this.fm =fm;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMatchVerticalBinding binding = ItemMatchVerticalBinding.inflate(inflater, parent, false);
        return new RecycleViewAdapterListBookingVertical.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

      holder.binding.score1.setText("2");
    }

    @Override
    public int getItemCount() {
//        return listPlayer.size();
        return 5;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        final ItemMatchVerticalBinding binding;
        public MyViewHolder(ItemMatchVerticalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

