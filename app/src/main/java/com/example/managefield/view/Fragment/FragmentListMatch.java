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
import com.example.managefield.data.enumeration.Result;
import com.example.managefield.data.enumeration.Status;
import com.example.managefield.databinding.FragmentListMatchBinding;
import com.example.managefield.databinding.LoadingLayoutBinding;
import com.example.managefield.view.Adapter.RecycleViewAdapterListMatchVertical;
import com.example.managefield.viewModel.ListMatchViewModel;
import com.example.managefield.Session.SessionStateData;
import com.example.managefield.viewModel.UpdateScoreViewModel;

import java.util.Map;

public class FragmentListMatch extends Fragment {
    private ListMatchViewModel viewModel ;
    private UpdateScoreViewModel updateScoreViewModel;
    private FragmentListMatchBinding binding;
    private Dialog loadingDialog;
    private LoadingLayoutBinding loadingLayoutBinding;
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
        loadingDialog = new Dialog(getContext());
        loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        loadingLayoutBinding = LoadingLayoutBinding.inflate(getLayoutInflater());
        loadingDialog.setContentView(loadingLayoutBinding.getRoot());
        loadingDialog.setCancelable(false);
        updateScoreViewModel = new ViewModelProvider(getActivity()).get(UpdateScoreViewModel.class);
        RecycleViewAdapterListMatchVertical adapter = viewModel.getAdapterListMatch();
        adapter.setFm(getParentFragmentManager());
        binding.recycleViewListVertical.setAdapter(viewModel.getAdapterListMatch());
        binding.recycleViewListVertical.setLayoutManager(new LinearLayoutManager(getContext()));
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
                    Toast.makeText(getContext(),"Cập nhập thành công",Toast.LENGTH_SHORT).show();
                    viewModel.getListMatch();
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
