package com.example.managefield.data.datasource;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.managefield.Interface.CallBack;
import com.example.managefield.model.Field;
import com.example.managefield.model.TimeGame;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TimeDataSource {
    private final String TAG = "UserDataSource";
    static TimeDataSource instance;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();


    public static TimeDataSource getInstance() {
        if (instance == null) {
            instance = new TimeDataSource();
        }
        return instance;
    }

    public void updateProfile(Map<String, Object> updateData, final CallBack<String,String> callBack) {
        db.collection("TimeGame").document((String) updateData.get("id")).update(updateData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                callBack.onSuccess(null);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                callBack.onFailure(e.getMessage());
            }
        });
    }

    public void loadListTime(String idTeam,final CallBack<List<TimeGame>,String> loadListTimeCallBack) {
        db.collection("Field").document(idTeam).collection("listTimeGame").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                final List<TimeGame> listTimeGame = new ArrayList<>();
                Log.d(TAG, "size: "+listTimeGame.size());
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                        TimeGame timeGame = document.toObject(TimeGame.class);
                        listTimeGame.add(timeGame);
                    }
                    loadListTimeCallBack.onSuccess(listTimeGame);
                }else {
                    loadListTimeCallBack.onSuccess(null);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loadListTimeCallBack.onFailure(e.getMessage());
            }
        });
    }

    public void createTeam(String idField,Map<String, Object> map,final CallBack<String, String> createTime) {
        DocumentReference ref = db.collection("Field").document(idField).collection("listTimeGame").document();
        map.put("id", ref.getId());
        ref.set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                createTime.onSuccess("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                createTime.onFailure(e.getMessage());
            }
        });
    }

    public void updateTime(String idField,Map<String,Object> map,final CallBack<String, String> updateTime) {
       db.collection("Field").document(idField).collection("listTimeGame").document((String) map.get("id")).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void aVoid) {
              updateTime.onSuccess("");
           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
           updateTime.onFailure("");
           }
       });
    }

    public void deleteTime(String idField,Map<String,Object> map,final CallBack<String, String> updateTime) {
        db.collection("Field").document(idField).collection("listTimeGame").document((String) map.get("id")).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                updateTime.onSuccess("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                updateTime.onFailure("");
            }
        });
    }
}
