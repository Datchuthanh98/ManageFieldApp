package com.example.managefield.view.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.managefield.databinding.FragmentUpdateScoreBinding;
import com.example.managefield.databinding.FragmentUpdateTimeBinding;
import com.example.managefield.model.TimeGame;
import com.example.managefield.viewModel.ListTimeViewModel;
import com.example.managefield.viewModel.UpdateScoreViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.HashMap;
import java.util.Map;

public class FragmentUpdateTime extends BottomSheetDialogFragment {

     private FragmentUpdateTimeBinding binding;
     private ListTimeViewModel viewModel;
     private  TimeGame timeGame;
     public FragmentUpdateTime(TimeGame timeGame ) {
        this.timeGame = timeGame;



    }

    private void setDefaultValute(TimeGame timeGame) {
        binding.txtStartTime.setText(timeGame.getStartTime());
        binding.txtEndTime.setText(timeGame.getEndTime());
        binding.txtCost.setText(timeGame.getCost());
        binding.txtPosition.setText(timeGame.getPosition());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUpdateTimeBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(ListTimeViewModel.class);
        super.onViewCreated(view, savedInstanceState);
        initComponent(view.getContext());
        setDefaultValute(this.timeGame);

    }


    private  void initComponent(final Context context){
         binding.btnSave.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 viewModel.updateTime(getScoreData());
                 detack();
             }
         });
    }

    private void detack(){
         dismiss();
    }



    private Map<String, Object> getScoreData() {
         HashMap<String,Object> data = new HashMap<>();
        data.put("startTime", binding.txtStartTime.getText().toString());
        data.put("endTime", binding.txtEndTime.getText().toString());
        data.put("cost", binding.txtCost.getText().toString());
        data.put("position", binding.txtPosition.getText().toString());
        data.put("idField", timeGame.getIdField());
        data.put("id",timeGame.getId());
        return data;
    }




}
