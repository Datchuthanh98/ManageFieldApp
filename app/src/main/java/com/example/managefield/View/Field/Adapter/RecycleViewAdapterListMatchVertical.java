package com.example.managefield.View.Field.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managefield.databinding.ItemMatchVerticalBinding;


public class RecycleViewAdapterListMatchVertical extends RecyclerView.Adapter<RecycleViewAdapterListMatchVertical.MyViewHolder> {
    private FragmentManager fm;
    public RecycleViewAdapterListMatchVertical(FragmentManager fm) {
        this.fm =fm;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMatchVerticalBinding binding = ItemMatchVerticalBinding.inflate(inflater, parent, false);
        return new RecycleViewAdapterListMatchVertical.MyViewHolder(binding);
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

