package com.example.managefield.view.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.managefield.databinding.FragmentAddTimeBinding;
import com.example.managefield.viewModel.ListTimeViewModel;
import com.example.managefield.Session.SessionField;

import java.util.HashMap;
import java.util.Map;


public class FragmentAddTimeDialog extends DialogFragment {

    private ListTimeViewModel viewModel ;
    FragmentAddTimeBinding binding;
    private Map<String, Object> data = new HashMap<>();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        binding = FragmentAddTimeBinding.inflate(inflater);
        builder.setView(binding.getRoot());
        return builder.create();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ListTimeViewModel.class);
        binding.setLifecycleOwner(this);
        initComponent(getContext());
        View view = binding.getRoot();
        return  view;
    }


    private void detach(){
        dismiss();
    }

    private  void initComponent(final Context context){
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.createTeam(dataTeam());
                detach();
            }
        });
    }


    private Map<String, Object> dataTeam() {
        data.put("startTime", binding.txtStartTime.getText().toString());
        data.put("endTime", binding.txtEndTime.getText().toString());
        data.put("position", binding.txtEndTime.getText().toString());
        data.put("cost", binding.txtCost.getText().toString());
        return data;
    }



}
