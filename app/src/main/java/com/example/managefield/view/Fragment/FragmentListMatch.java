package com.example.managefield.view.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class FragmentListMatch extends Fragment {
    private ListMatchViewModel viewModel ;
    private UpdateScoreViewModel updateScoreViewModel;
    private FragmentListMatchBinding binding;
    private Dialog loadingDialog;
    private LoadingLayoutBinding loadingLayoutBinding;
    private long timeStartLong;
    private long timeEndLong;
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
        setDafaultDate();
        observerLiveData();
        initComponent();
    }



    private void observerLiveData() {
        SessionStateData.getInstance().getDatalistMatch().observe(getViewLifecycleOwner(), new Observer<DataState>() {
            @Override
            public void onChanged(DataState dataState) {
                viewModel.getListMatch(timeStartLong ,timeEndLong);
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
                    viewModel.getListMatch(timeStartLong,timeEndLong);
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


     private void initComponent(){
         binding.dateStart.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Calendar c = Calendar.getInstance();
                 Dialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                     @Override
                     public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                         String sday, smonth;
                         if(day<10){
                             sday ="0"+day;
                         }else{
                             sday=""+day;
                         }

                         if(month<9){
                             smonth ="0"+(month+1);
                         }else{
                             smonth=""+(month+1);
                         }

                         String dateString = sday + "/" + smonth + "/" + year;

                         binding.dateStart.setText(dateString);

                         Calendar calendar = Calendar.getInstance();
                         calendar.set(year, month, day,0,0,0);
                         timeStartLong = calendar.getTimeInMillis();
                         timeStartLong = timeStartLong/1000;
                         timeStartLong = timeStartLong * 1000;



                     }
                 }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                 datePickerDialog.show();
             }
         });


         binding.dateEnd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Calendar c = Calendar.getInstance();
                 Dialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                     @Override
                     public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                         String sday, smonth;
                         if(day<10){
                             sday ="0"+day;
                         }else{
                             sday=""+day;
                         }

                         if(month<9){
                             smonth ="0"+(month+1);
                         }else{
                             smonth=""+(month+1);
                         }

                         String dateString = sday + "/" + smonth + "/" + year;

                         binding.dateEnd.setText(dateString);

                         Calendar calendar = Calendar.getInstance();
                         calendar.set(year, month, day,0,0,0);
                         timeEndLong = calendar.getTimeInMillis();
                         timeEndLong = timeEndLong/1000;
                         timeEndLong = timeEndLong * 1000;

                     }
                 }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                 datePickerDialog.show();
             }
         });

         binding.searchDate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(getContext(),"Search Date ",Toast.LENGTH_SHORT).show();
                 Map<String ,Object> map = new HashMap<>();

                 viewModel.getListMatchByDate(timeStartLong,timeEndLong);
             }
         });
     }



    private void setDafaultDate(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        String dateString = day + "/" + (month+1) + "/" + year;
        binding.dateStart.setText(dateString);
        binding.dateEnd.setText(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day,0,0,0);
        timeStartLong = calendar.getTimeInMillis();
        timeStartLong = timeStartLong/1000;
        timeStartLong = timeStartLong * 1000;
        timeEndLong = timeStartLong;
    }



}
