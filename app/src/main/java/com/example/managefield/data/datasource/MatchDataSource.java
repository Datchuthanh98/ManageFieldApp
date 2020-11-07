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
                Log.d("match", "Success: "+listTeamMaps.size());
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
                Log.d("match", "failure: "+e.getMessage());
            }
        });
    }


    public void loadListMatch(final CallBack<List<Match>,String> loadListMatchCallBack) {
        functions.getHttpsCallable("getAllListMatch").call().addOnSuccessListener(new OnSuccessListener<HttpsCallableResult>() {
            @Override
            public void onSuccess(HttpsCallableResult httpsCallableResult) {
                Gson gson= new Gson();
                List<Map> listTeamMaps = (List<Map>) httpsCallableResult.getData();
                List<Match> listMatch = new ArrayList<>();
                if(listTeamMaps == null){
                    loadListMatchCallBack.onSuccess(null);
                }else{
                    for (Map teamMap : listTeamMaps){
                        Match match = gson.fromJson(gson.toJson(teamMap), Match.class);
                        listMatch.add(match);
                    }
                    loadListMatchCallBack.onSuccess(listMatch);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loadListMatchCallBack.onFailure(e.getMessage());
            }
        });

    }


    public void acceptBooking(final String idBooking, final CallBack<String,String> acceptBooking) {
        DocumentReference ref = db.collection("Match").document();
        Map<String, Object> map = new HashMap<>();
        map.put("id", ref.getId());
        map.put("idBooking", idBooking);
        map.put("scoreHome", null);
        map.put("scoreAway", null);
        map.put("active", false);
        ref.set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                db.collection("Booking").document(idBooking).update("approve", true).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        acceptBooking.onSuccess("");
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                acceptBooking.onFailure(e.getMessage());
            }
        });

    }

    public void declineBooking(String idBooking, final CallBack declineBooking) {

        db.collection("Booking").document(idBooking).update("approve", true).addOnSuccessListener(new OnSuccessListener<Void>() {
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
