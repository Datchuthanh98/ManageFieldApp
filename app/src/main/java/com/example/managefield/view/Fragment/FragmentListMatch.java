package com.example.managefield.view.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.managefield.data.enumeration.DataState;
import com.example.managefield.data.enumeration.Result;
import com.example.managefield.databinding.FragmentListMatchBinding;
import com.example.managefield.view.Adapter.RecycleViewAdapterListMatchVertical;
import com.example.managefield.viewModel.ListMatchViewModel;
import com.example.managefield.Session.SessionStateData;
import com.example.managefield.viewModel.UpdateScoreViewModel;

import java.util.Map;

public class FragmentListMatch extends Fragment {
    private ListMatchViewModel viewModel ;
    private UpdateScoreViewModel updateScoreViewModel;
    private FragmentListMatchBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListMatchBinding.inflate(inflater);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ListMatchViewModel.class);
        updateScoreViewModel = new ViewModelProvider(getActivity()).get(UpdateScoreViewModel.class);
        RecycleViewAdapterListMatchVertical adapter = viewModel.getAdapterListMatch();
        adapter.setFm(getParentFragmentManager());
        binding.recycleViewListBookingVertical.setAdapter(viewModel.getAdapterListMatch());
        binding.recycleViewListBookingVertical.setLayoutManager(new LinearLayoutManager(getContext()));
        observerLiveData();
    }



    private void observerLiveData() {
        SessionStateData.getInstance().getDatalistMatch().observe(getViewLifecycleOwner(), new Observer<DataState>() {
            @Override
            public void onChanged(DataState dataState) {
                viewModel.getListMatch();
            }
        });

        updateScoreViewModel.getScoreList().observe(getViewLifecycleOwner(), new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(Map<String, Object> map) {
                if(map !=null){
                    viewModel.updateScoreMatch(map);
                    updateScoreViewModel.setScoreList(null);
                }
            }
        });

        viewModel.getResultUpdateScore().observe(getViewLifecycleOwner(), new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                if(result == Result.SUCCESS){
                    Toast.makeText(getContext(),"Update List Match",Toast.LENGTH_SHORT).show();
                    viewModel.getListMatch();
                }
            }
        });
    }



}
