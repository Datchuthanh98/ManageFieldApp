package com.example.managefield.data.datasource;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.managefield.Interface.CallBack;
import com.example.managefield.model.Field;
import com.example.managefield.Session.SessionField;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldDataSource {
    private final String TAG = "UserDataSource";
    static FieldDataSource instance;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();


    public static FieldDataSource getInstance() {
        if (instance == null) {
            instance = new FieldDataSource();
        }
        return instance;
    }


    public void login(String email, final String password, final CallBack<Field,String> callBack) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = authResult.getUser();
                       db.collection("Field").document(user.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                           @Override
                           public void onSuccess(DocumentSnapshot documentSnapshot) {
                             Field field = documentSnapshot.toObject(Field.class);
                               callBack.onSuccess(field);
                           }
                       });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                       callBack.onFailure(e.getMessage());
                    }
                });
    }

    public void updateProfile(Map<String, Object> updateData, final CallBack<String,String> callBack) {
        String uid = SessionField.getInstance().getPlayerLiveData().getValue().getId();
        db.collection("Field").document(uid).update(updateData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                callBack.onSuccess("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                callBack.onFailure(e.getMessage());
            }
        });
    }


    public void updateImage(Uri uri, String path ,boolean isAvatar, final CallBack<String,String> callBack){
        final String uid = SessionField.getInstance().getPlayerLiveData().getValue().getId();
        Date date = new Date();
        String urlFile ="", key="";
        String[] parts = path.split("\\.");
        if(isAvatar) {
            key="urlAvatar";
            urlFile = "/Avatar/"+uid+"_"+date.getTime()+"."+parts[1];

        }else{
            key="urlCover";
            urlFile = "/Cover/"+uid+"_"+date.getTime()+"."+parts[1];
        }

        final String finalUrlFile = urlFile;
        final String finalKey = key;
        storage.getReference().child(urlFile).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Map<String,Object> map = new HashMap<>();

                map.put(finalKey, finalUrlFile);// "avatar/dsa.jpg"
                db.collection("Field").document(uid).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        callBack.onSuccess(finalUrlFile);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callBack.onFailure(e.getMessage());
            }
        });
    }

    public FileDownloadTask getUserPhoto(String url, File downloadLocation) {
        StorageReference fileRef = storage.getReference().child(url);
        return fileRef.getFile(downloadLocation);
    }
}
