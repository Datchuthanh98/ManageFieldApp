package com.example.managefield.View.Field.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.managefield.R;
import com.example.managefield.databinding.FragmentEditFieldBasicBinding;


public class FragmentEditFieldField extends Fragment {

     private ImageButton btnSave;
     private FragmentEditFieldBasicBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_edit_field_basic_,container,false);
          binding = DataBindingUtil.inflate(inflater,R.layout.fragment_edit_field_basic_,container,false);
        View view = binding.getRoot();
        return  view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Toolbar toolbar = view.findViewById(R.id.toolbar);
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_back_white_24);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detach();
            }
        });

        btnSave = view.findViewById(R.id.image_btn_2);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detach();
            }
        });

    }

    private void detach(){
        getParentFragmentManager().popBackStack();
        getParentFragmentManager().beginTransaction().detach(this).commit();
    }
}
