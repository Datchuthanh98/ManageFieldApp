package com.example.managefield.view.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.managefield.databinding.FragmentUpdateScoreBinding;
import com.example.managefield.viewModel.UpdateScoreViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.HashMap;
import java.util.Map;

public class FragmentUpdateScore extends BottomSheetDialogFragment {

     private FragmentUpdateScoreBinding binding;
     private UpdateScoreViewModel viewModel;
     private  String idMatch;
     public FragmentUpdateScore(String idMatch ) {
        this.idMatch = idMatch;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUpdateScoreBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(UpdateScoreViewModel.class);
        super.onViewCreated(view, savedInstanceState);
        initComponent(view.getContext());

    }


    private  void initComponent(final Context context){
         binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 viewModel.setScoreList(getScoreData());
                 detack();
             }
         });


    }

    private void detack(){
         dismiss();
    }



    private Map<String, Object> getScoreData() {
         HashMap<String,Object> data = new HashMap<>();
        data.put("scoreHome", Integer.parseInt(binding.txtScoreTeamHome.getText().toString()));
        data.put("scoreAway", Integer.parseInt(binding.txtScoreTeamAway.getText().toString()));
        data.put("active", true);
        data.put("id",idMatch);
        return data;
    }




}
