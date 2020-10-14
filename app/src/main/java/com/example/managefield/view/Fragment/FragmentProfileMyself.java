package com.example.managefield.view.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.managefield.auth.ActivityLogin;
import com.example.managefield.databinding.FragmentProfileMyselfBinding;
import com.example.managefield.main.ActivityMainField;
import com.example.managefield.viewModel.PlayerViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class FragmentProfileMyself extends Fragment {

    private FragmentProfileMyselfBinding binding;
    private PlayerViewModel session = PlayerViewModel.getInstance();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileMyselfBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityMainField activityHome = (ActivityMainField) getContext();
                activityHome.addFragment(new FragmentEditMainPlayer());
            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), ActivityLogin.class));
                getActivity().finish();
            }
        });


        observeLiveData(view.getContext());


    }


    private void observeLiveData(final Context context) {
       // CreatePhoto
//        PlayerViewModel.getInstance().getAvatarLiveData().observe(getViewLifecycleOwner(), new Observer<File>() {
//            @Override
//            public void onChanged(File file) {
//                Picasso.get().load(file).into(binding.avatar);
//            }
//        });
//
//        PlayerViewModel.getInstance().getCoverLiveData().observe(getViewLifecycleOwner(), new Observer<File>() {
//            @Override
//            public void onChanged(File file) {
//                Picasso.get().load(file).into(binding.cover);
//            }
//        });
//
//        session.getResultPhotoLiveData().observe(getViewLifecycleOwner(), new Observer<Result>() {
//            @Override
//            public void onChanged(Result result) {
//                if (result == null) return;
//                if (result == Result.SUCCESS) {
//                    Picasso.get().load(session.getAvatarLiveData().getValue()).into(binding.avatar);
//                    Picasso.get().load(session.getCoverLiveData().getValue()).into(binding.cover);
//
//                } else if (result == Result.FAILURE) {
//                    Toast.makeText(context, session.getResultMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
     }

    }
