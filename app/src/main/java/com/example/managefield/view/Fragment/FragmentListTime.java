package com.example.managefield.view.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.managefield.data.enumeration.DataState;
import com.example.managefield.data.enumeration.Result;
import com.example.managefield.databinding.FragmentListMatchBinding;
import com.example.managefield.databinding.FragmentListTiemBinding;
import com.example.managefield.view.Adapter.RecycleViewAdapterListMatchVertical;
import com.example.managefield.view.Adapter.RecycleViewAdapterListTimeVertical;
import com.example.managefield.viewModel.ListMatchViewModel;
import com.example.managefield.viewModel.ListTimeViewModel;
import com.example.managefield.viewModel.SessionStateData;

public class FragmentListTime extends Fragment {
    private ListTimeViewModel viewModel ;
    private FragmentListTiemBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListTiemBinding.inflate(inflater);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ListTimeViewModel.class);
        RecycleViewAdapterListTimeVertical adapter = viewModel.getAdapterListTime();
        adapter.setFm(getParentFragmentManager());
        binding.recycleViewListTimeVertical.setAdapter(viewModel.getAdapterListTime());
        binding.recycleViewListTimeVertical.setLayoutManager(new LinearLayoutManager(getContext()));
        observerLiveDate();
        binding.btnCreateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new FragmentAddTimeDialog();
                dialogFragment.show(getParentFragmentManager(),"Add Time Diaglog");
            }
        });
    }

    private void observerLiveDate() {
        SessionStateData.getInstance().getDatalistTime().observe(getViewLifecycleOwner(), new Observer<DataState>() {
            @Override
            public void onChanged(DataState dataState) {
                viewModel.getListTime();
            }
        });

    }





}
