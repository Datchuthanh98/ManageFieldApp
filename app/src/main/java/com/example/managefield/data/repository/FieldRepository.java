package com.example.managefield.data.repository;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.managefield.Interface.CallBack;


import com.example.managefield.data.datasource.FieldDataSource;
import com.example.managefield.model.Field;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;

import java.io.File;
import java.util.Map;

public class FieldRepository {
    private static FieldRepository instance;
    private FieldDataSource userDataSource = FieldDataSource.getInstance();

    private FieldRepository() {
    }

    public static FieldRepository getInstance() {
        if (instance == null) {
            instance = new FieldRepository();
        }
        return instance;
    }

    public void updateProfile(Map<String, Object> updateInformation, CallBack<String,String> callBack) {
        userDataSource.updateProfile(updateInformation, callBack);
    }

    public void updateImage(Uri uri, String path ,boolean isAvatar, final CallBack<String,String> callBack){
        userDataSource.updateImage(uri,path,isAvatar,callBack);
    }


    public void getUserPhoto(final CallBack<File,String> callBack, String url, Context context) {
        if (url.isEmpty()) {
            callBack.onSuccess(null);
            return;
        }
        String[] files = url.split("/");
        String fileName = files[files.length-1];
        final File cachePhoto = new File(context.getCacheDir(), fileName);
        FileDownloadTask downloadTask = userDataSource.getUserPhoto(url, cachePhoto);
        downloadTask.addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                callBack.onSuccess(cachePhoto);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callBack.onSuccess(null);
            }
        });
    }


    public void getCoverPhoto(final CallBack<File,String> callBack, String url, Context context) {
        if (url.isEmpty()) {
            callBack.onSuccess(null);
            return;
        }
        String[] files = url.split("/");
        String fileName = files[files.length-1];
        final File cachePhoto = new File(context.getCacheDir(), fileName);
        FileDownloadTask downloadTask = userDataSource.getUserPhoto(url, cachePhoto);
        downloadTask.addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                callBack.onSuccess(cachePhoto);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callBack.onSuccess(null);
            }
        });
    }



}
