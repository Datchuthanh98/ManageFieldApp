package com.example.managefield.data.datasource;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.managefield.Interface.AcceptBooking;
import com.example.managefield.Interface.AddBookingField;
import com.example.managefield.Interface.DeclineBooking;
import com.example.managefield.Interface.LoadListBookingCallBack;
import com.example.managefield.Interface.LoadListMatchCallBack;
import com.example.managefield.model.Booking;
import com.example.managefield.model.Match;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchDataSource {
    private static final String TAG = "matchdata";
    static MatchDataSource instance;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public static MatchDataSource getInstance() {
        if (instance == null) {
            instance = new MatchDataSource();
        }
        return instance;
    }

    public void loadListBooking(final LoadListBookingCallBack loadListBookingCallBack) {
        db.collection("Booking").whereEqualTo("approve", null).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Booking> bookingList = new ArrayList<>();
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                        Booking booking = document.toObject(Booking.class);

                        bookingList.add(booking);
                    }
                    loadListBookingCallBack.onSuccess(bookingList);
                } else {
                    loadListBookingCallBack.onSuccess(null);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }


    public void loadListMatch(final LoadListMatchCallBack loadListMatchCallBack) {
        db.collection("Match").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Match> matchList = new ArrayList<>();
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                        Match match = document.toObject(Match.class);
                        matchList.add(match);
                    }

                    loadListMatchCallBack.onSuccess(matchList);
                } else {
                    loadListMatchCallBack.onSuccess(null);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }


    public void acceptBooking(final String idBooking, final AcceptBooking acceptBooking) {
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
                        acceptBooking.onSuccess();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                acceptBooking.onFailure();
            }
        });


    }

    public void declineBooking(String idBooking, final DeclineBooking declineBooking) {

        db.collection("Booking").document(idBooking).update("approve", true).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                declineBooking.onSuccess();
            }
        });
    }

}
