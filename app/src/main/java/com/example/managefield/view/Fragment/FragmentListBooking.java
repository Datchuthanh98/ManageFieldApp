package com.example.managefield.view.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.managefield.data.enumeration.DataState;
import com.example.managefield.data.enumeration.LoadingState;
import com.example.managefield.data.enumeration.Status;
import com.example.managefield.databinding.FragmentListBookingBinding;
import com.example.managefield.databinding.LoadingLayoutBinding;
import com.example.managefield.view.Adapter.RecycleViewAdapterListBookingVertical;
import com.example.managefield.viewModel.ListBookingViewModel;
import com.example.managefield.Session.SessionField;
import com.example.managefield.Session.SessionStateData;

public class FragmentListBooking extends Fragment {
    private ListBookingViewModel viewModel ;
    private FragmentListBookingBinding binding;
    private Dialog loadingDialog;
    private LoadingLayoutBinding loadingLayoutBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListBookingBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ListBookingViewModel.class);
        loadingDialog = new Dialog(getContext());
        loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        loadingLayoutBinding = LoadingLayoutBinding.inflate(getLayoutInflater());
        loadingDialog.setContentView(loadingLayoutBinding.getRoot());
        loadingDialog.setCancelable(false);


        RecycleViewAdapterListBookingVertical adapter = viewModel.getAdapterListBooking();
        adapter.setFm(getParentFragmentManager());
        binding.recycleViewListVertical.setAdapter(viewModel.getAdapterListBooking());
        binding.recycleViewListVertical.setLayoutManager(new LinearLayoutManager(getContext()));
        observerLiveDate();
    }

    private void observerLiveDate() {
        SessionStateData.getInstance().getDatalistBooking().observe(getViewLifecycleOwner(), new Observer<DataState>() {
            @Override
            public void onChanged(DataState dataState) {
                viewModel.getListBooking(SessionField.getInstance().getPlayerLiveData().getValue().getId());
            }
        });

        viewModel.getTeamLoadState().observe(getViewLifecycleOwner(), new Observer<LoadingState>() {
            @Override
            public void onChanged(LoadingState loadingState) {
                if (loadingState == null) return;
                if (loadingState == LoadingState.INIT) {
                    binding.loadingLayoutt.setVisibility(View.VISIBLE);
                } else if (loadingState == LoadingState.LOADING) {
                    binding.loadingLayoutt.setVisibility(View.VISIBLE);
                } else if (loadingState == LoadingState.LOADED) {
                    binding.loadingLayoutt.setVisibility(View.GONE);
                }
            }
        });

        viewModel.getExecuteState().observe(getViewLifecycleOwner(), new Observer<LoadingState>() {
            @Override
            public void onChanged(LoadingState loadingState) {
                if (loadingState == null) return;
                if (loadingState == LoadingState.INIT) {
                    loadingDialog.show();
                } else if (loadingState == LoadingState.LOADING) {
                } else if (loadingState == LoadingState.LOADED) {
                    loadingDialog.dismiss();
                }
            }
        });

        viewModel.getStatusData().observe(getViewLifecycleOwner(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                if(status == Status.NO_DATA){
                    binding.viewNoData.setVisibility(View.VISIBLE);
                }else if(status == status.EXIST_DATA){
                    binding.viewNoData.setVisibility(View.GONE);
                }
            }
        });
    }



}
