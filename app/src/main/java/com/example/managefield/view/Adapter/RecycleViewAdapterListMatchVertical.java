package com.example.managefield.view.Adapter;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managefield.R;
import com.example.managefield.databinding.ItemBookingVerticalBinding;
import com.example.managefield.databinding.ItemMatchVerticalBinding;
import com.example.managefield.main.ActivityMain;
import com.example.managefield.model.Booking;
import com.example.managefield.model.Match;
import com.example.managefield.view.Fragment.FragmentEditFieldIntroduce;
import com.example.managefield.view.Fragment.FragmentUpdateScore;
import com.example.managefield.viewModel.ListBookingViewModel;
import com.example.managefield.viewModel.ListMatchViewModel;
import com.example.managefield.viewModel.UpdateScoreViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



//import com.example.managefield.view.Fragment.FragmentMainProfileMatch;


public class RecycleViewAdapterListMatchVertical extends RecyclerView.Adapter<RecycleViewAdapterListMatchVertical.MyViewHolder> {
    private FragmentManager fm;
    private List<Match> matches = new ArrayList<>();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();



    public RecycleViewAdapterListMatchVertical() {
    }


    public RecycleViewAdapterListMatchVertical(FragmentManager fm) {
        this.fm = fm;
    }



    public void setFm(FragmentManager fm) {
        this.fm = fm;
    }

    public  void  setListMatch(List<Match> listMatch){
        this.matches = listMatch;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMatchVerticalBinding binding = ItemMatchVerticalBinding.inflate(inflater, parent, false);
        return new MyViewHolder(binding);
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        final ItemMatchVerticalBinding binding;
        public MyViewHolder(ItemMatchVerticalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragment dialog = new FragmentUpdateScore(matches.get(position).getId());
                dialog.show(((ActivityMain) holder.itemView.getContext()).getSupportFragmentManager(), null);
            }
        });



        if(matches.get(position).getIdBooking().getIdTeamHome().getUrlAvatar() !=null) {
            storageRef.child(matches.get(position).getIdBooking().getIdTeamHome().getUrlAvatar()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    if (uri != null) {
                        Picasso.get().load(uri).fit().centerCrop().into(holder.binding.avatarHome);
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {

                }
            });
        }



        if(matches.get(position).getIdBooking().getIdTeamAway() != null) {
            holder.binding.avatarAway.setImageResource(R.drawable.avatar_team_default);
            storageRef.child(matches.get(position).getIdBooking().getIdTeamAway().getUrlAvatar()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    if (uri != null) {
                            Picasso.get().load(uri).fit().centerCrop().into(holder.binding.avatarAway);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {

                }
            });
        }
        
        holder.binding.setMatch(matches.get(position));




        Calendar calendar = Calendar.getInstance();


        calendar.setTimeInMillis(matches.get(position).getIdBooking().getDate());
        int pYear=calendar.get(Calendar.YEAR);
        int pMonth=calendar.get(Calendar.MONTH);
        int pDay=calendar.get(Calendar.DAY_OF_MONTH);
        String startTime = matches.get(position).getIdBooking().getStartTime();
        String endTime = matches.get(position).getIdBooking().getEndTime();
        holder.binding.txtTime.setText(pDay+"/"+(pMonth+1)+"/"+pYear+","+startTime+"-"+endTime);
        holder.binding.txtField.setText("Sân thứ " + matches.get(position).getIdBooking().getPosition());

        String cutEndTime[] = matches.get(position).getIdBooking().getEndTime().split(":",2);
        int pHourEnd=Integer.parseInt(cutEndTime[0]);
        int mMinuteEnd= Integer.parseInt(cutEndTime[1]);

        String cutStartTime[] = matches.get(position).getIdBooking().getStartTime().split(":",2);
        int pHourStart=Integer.parseInt(cutStartTime[0]);
        int mMinuteStart= Integer.parseInt(cutStartTime[1]);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(pYear,pMonth,pDay,pHourEnd,mMinuteEnd);
        long timeGameEnd = calendar2.getTimeInMillis();

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(pYear,pMonth,pDay,pHourStart,mMinuteStart);
        long timeGameStart = calendar3.getTimeInMillis();

        Calendar calendar4 = Calendar.getInstance();
        long timeNow = calendar4.getTimeInMillis();


        if(timeNow < timeGameStart){
            holder.binding.btnUpdate.setVisibility(View.GONE);
            holder.binding.status.setText("Sắp diễn ra");
        }else if(timeGameStart <= timeNow && timeNow  <= timeGameEnd){
            holder.binding.btnUpdate.setVisibility(View.GONE);
            holder.binding.status.setText("Đang diễn ra ");
        }else {
            holder.binding.btnUpdate.setVisibility(View.VISIBLE);
            holder.binding.status.setText("Đã kết thúc ");
        }






    }

    @Override
    public int getItemCount() {
        return matches.size();
    }


}

