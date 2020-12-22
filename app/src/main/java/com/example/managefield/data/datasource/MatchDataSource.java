package com.example.managefield.data.datasource;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.managefield.Interface.CallBack;
import com.example.managefield.model.Booking;
import com.example.managefield.model.Match;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchDataSource {
    static MatchDataSource instance;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseFunctions functions = FirebaseFunctions.getInstance();


    public static MatchDataSource getInstance() {
        if (instance == null) {
            instance = new MatchDataSource();
        }
        return instance;
    }

    public void loadListBooking(String idField,final CallBack<List<Booking>,String> loadListBookingCallBack) {
        functions.getHttpsCallable("getListBookingByField").call(idField).addOnSuccessListener(new OnSuccessListener<HttpsCallableResult>() {
            @Override
            public void onSuccess(HttpsCallableResult httpsCallableResult) {
                Gson gson= new Gson();
                List<Map> listTeamMaps = (List<Map>) httpsCallableResult.getData();
                List<Booking> listBooking = new ArrayList<>();
                if(listTeamMaps == null){
                    loadListBookingCallBack.onSuccess(null);
                }else{

                    for (Map teamMap : listTeamMaps){
                        Booking booking = gson.fromJson(gson.toJson(teamMap), Booking.class);
                        listBooking.add(booking);
                    }
                    loadListBookingCallBack.onSuccess(listBooking);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loadListBookingCallBack.onFailure("");
            }
        });
    }


    public void loadListMatch(String idField ,final CallBack<List<Match>,String> loadListMatchCallBack) {
        functions.getHttpsCallable("getListMatchByField").call(idField).addOnSuccessListener(new OnSuccessListener<HttpsCallableResult>() {
            @Override
            public void onSuccess(HttpsCallableResult httpsCallableResult) {
                Gson gson= new Gson();
                List<Map> listTeamMaps = (List<Map>) httpsCallableResult.getData();
                List<Match> listBooking = new ArrayList<>();
                if(listTeamMaps == null){
                    loadListMatchCallBack.onSuccess(null);
                }else{

                    for (Map teamMap : listTeamMaps){
                        Match booking = gson.fromJson(gson.toJson(teamMap), Match.class);
                        listBooking.add(booking);
                    }
                    loadListMatchCallBack.onSuccess(listBooking);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loadListMatchCallBack.onFailure("");
            }
        });

    }


    public void acceptBooking(final Booking booking, final CallBack<String,String> acceptBooking) {
        Map<String, Object> map = new HashMap<>();
        map.put("idBooking", booking.getId());
        map.put("idTeamHome",booking.getIdTeamHome().getId());
        map.put("idField",booking.getIdField().getId());
        map.put("date",booking.getDate());
        functions.getHttpsCallable("createMatch").call(map).addOnSuccessListener(new OnSuccessListener<HttpsCallableResult>() {
            @Override
            public void onSuccess(HttpsCallableResult httpsCallableResult) {
                acceptBooking.onSuccess("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("addbooking", "onFailure: "+e.getMessage());
                acceptBooking.onFailure(e.getMessage());
            }
        });

    }

    public void declineBooking(Booking booking, final CallBack declineBooking) {

        db.collection("Booking").document(booking.getId()).update("approve", false).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                declineBooking.onSuccess("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                declineBooking.onSuccess(e.getMessage());

            }
        });
    }

    public void updateScoreMatch(Map<String,Object>map , final  CallBack<String,String> callBack){
        db.collection("Match").document((String) map.get("id")).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                callBack.onSuccess("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callBack.onFailure("");
            }
        });
    }

}
