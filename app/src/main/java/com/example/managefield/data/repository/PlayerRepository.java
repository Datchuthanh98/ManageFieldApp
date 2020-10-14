package com.example.managefield.data.repository;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.managefield.Interface.GetUserCoverCallBack;
import com.example.managefield.Interface.GetUserPhotoCallBack;
import com.example.managefield.Interface.LoadListOtherPlayerCallBack;
import com.example.managefield.Interface.LoadListPlayerCallBack;
import com.example.managefield.Interface.LoadListPlayerRequestCallBack;
import com.example.managefield.Interface.UpdateImageCallBack;
import com.example.managefield.Interface.UpdateProfileCallBack;

import com.example.managefield.data.datasource.PlayerDataSource;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;

import java.io.File;
import java.util.Map;

public class PlayerRepository {
    private static PlayerRepository instance;
    private PlayerDataSource userDataSource = PlayerDataSource.getInstance();

    private PlayerRepository() {
    }

    public static PlayerRepository getInstance() {
        if (instance == null) {
            instance = new PlayerRepository();
        }
        return instance;
    }

    public void updateProfile(Map<String, Object> updateInformation, UpdateProfileCallBack callBack) {
        userDataSource.updateProfile(updateInformation, callBack);
    }

    public void updateImage(Uri uri, String path ,boolean isAvatar, final UpdateImageCallBack callBack){
        userDataSource.updateImage(uri,path,isAvatar,callBack);
    }


    public void getUserPhoto(final GetUserPhotoCallBack callBack, String url, Context context) {
        if (url.isEmpty()) {
            callBack.onGetUserPhotoCallBack(null);
            return;
        }
        String[] files = url.split("/");
        String fileName = files[files.length-1];
        final File cachePhoto = new File(context.getCacheDir(), fileName);
        FileDownloadTask downloadTask = userDataSource.getUserPhoto(url, cachePhoto);
        downloadTask.addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                callBack.onGetUserPhotoCallBack(cachePhoto);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callBack.onGetUserPhotoCallBack(null);
            }
        });
    }


    public void getCoverPhoto(final GetUserCoverCallBack callBack, String url, Context context) {
        if (url.isEmpty()) {
            callBack.onGetUserCoverCallBack(null);
            return;
        }
        String[] files = url.split("/");
        String fileName = files[files.length-1];
        final File cachePhoto = new File(context.getCacheDir(), fileName);
        FileDownloadTask downloadTask = userDataSource.getUserPhoto(url, cachePhoto);
        downloadTask.addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                callBack.onGetUserCoverCallBack(cachePhoto);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callBack.onGetUserCoverCallBack(null);
            }
        });
    }

    public void getListPlayer(String id, LoadListPlayerCallBack loadListPlayerCallBack){
        userDataSource.loadListPlayer(id,loadListPlayerCallBack);
    }

    public void getListPlayerRequest(String id, LoadListPlayerRequestCallBack loadListPlayerCallBack){
        userDataSource.loadListPlayerRequest(id,loadListPlayerCallBack);
    }

    public void getListOtherPlayer(String id ,LoadListOtherPlayerCallBack loadListOtherPlayerCallBack){
        userDataSource.loadListOtherPlayer(id,loadListOtherPlayerCallBack);
    }






}
