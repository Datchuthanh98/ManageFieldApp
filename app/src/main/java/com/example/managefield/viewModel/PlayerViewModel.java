package com.example.managefield.viewModel;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.managefield.Interface.GetUserCoverCallBack;
import com.example.managefield.Interface.GetUserPhotoCallBack;
import com.example.managefield.Interface.UpdateImageCallBack;
import com.example.managefield.Interface.UpdateProfileCallBack;
import com.example.managefield.Interface.UserChangeCallBack;
import com.example.managefield.data.enumeration.Result;
import com.example.managefield.data.repository.PlayerRepository;
import com.example.managefield.model.Player;

import java.io.File;
import java.util.Calendar;
import java.util.Map;

public class PlayerViewModel extends ViewModel implements UserChangeCallBack {
    private static PlayerViewModel instance;
    private Application application;
    private PlayerRepository playerRepository = PlayerRepository.getInstance();
    private MutableLiveData<Player> playerLiveData = new MutableLiveData<>();
    private MutableLiveData<File> playerAvatarLiveData= new MutableLiveData<>();
    private MutableLiveData<File> playerCoverLiveData = new MutableLiveData<>();
    private MutableLiveData<Result> resultLiveData = new MutableLiveData<>(null);
    private MutableLiveData<Result> resultPhotoLiveData = new MutableLiveData<>(null);
    private String resultMessage = null;

    public static PlayerViewModel getInstance() {
        if (instance == null) {
            instance = new PlayerViewModel();
        }
        return instance;
    }

    public Context getApplicationContext(){
        return application.getApplicationContext();
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public LiveData<Player> getPlayerLiveData() {
        return playerLiveData;
    }

    public void setPlayerLiveData(Player player){
        playerLiveData.setValue(player);
    }

    public LiveData<File>  getAvatarLiveData() {return  playerAvatarLiveData ;};

    public LiveData<File>  getCoverLiveData() {return  playerCoverLiveData ;};


    @Override
    public void onUserChange(Player player) {
        playerLiveData.setValue(player);
        if (player != null) {
            if (!player.getUrlAvatar().isEmpty()) {
                String[] files = player.getUrlAvatar().split("/");
                String fileName = files[files.length-1];
                File photo = new File(getApplicationContext().getCacheDir(), fileName);
                // 24 hours
                if (photo.exists() && photo.lastModified() < Calendar.getInstance().getTimeInMillis() - 86400000){
                    playerAvatarLiveData.setValue(photo);
                } else {
                    playerRepository.getUserPhoto(new GetUserPhotoCallBack() {
                        @Override
                        public void onGetUserPhotoCallBack(File photo) {
                            playerAvatarLiveData.setValue(photo);
                        }
                    }, player.getUrlAvatar(), getApplicationContext());
                }
            }

            if (!player.getUrlCover().isEmpty()) {
                String[] files = player.getUrlCover().split("/");
                String fileName = files[files.length-1];
                File photo = new File(getApplicationContext().getCacheDir(), fileName);
                // 24 hours
                if (photo.exists() && photo.lastModified() < Calendar.getInstance().getTimeInMillis() - 86400000){
                    playerCoverLiveData.setValue(photo);
                } else {
                    playerRepository.getCoverPhoto(new GetUserCoverCallBack() {
                        @Override
                        public void onGetUserCoverCallBack(File photo) {
                            playerCoverLiveData.setValue(photo);
                        }
                    }, player.getUrlCover(), getApplicationContext());
                }
            }


        }
    }

    public LiveData<Result> getResultLiveData() {
        return resultLiveData;
    }


    public LiveData<Result> getResultPhotoLiveData() {
        return resultPhotoLiveData;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void updateProfile(Map<String, Object> updateBasic) {
        playerRepository.updateProfile(updateBasic, new UpdateProfileCallBack() {
            @Override
            public void onSuccess() {
                resultLiveData.setValue(Result.SUCCESS);
            }

            @Override
            public void onFailure(String message) {
                resultMessage = message;
                resultLiveData.setValue(Result.FAILURE);
            }
        });
    }

    public  void updateImage(Uri uri, final String path , final boolean isAvatar){

        playerRepository.updateImage(uri, path, isAvatar, new UpdateImageCallBack() {
            @Override
            public void onSuccess(String url) {
                Log.d("check updateUI", "onSuccess: VAO DAY R NE");
                resultPhotoLiveData.setValue(Result.SUCCESS);
                Player player = getInstance().playerLiveData.getValue();
                if (isAvatar){
                    player.setUrlAvatar(url);
                } else {
                    player.setUrlCover(url);
                }
                getInstance().onUserChange(player);
            }

            @Override
            public void onFailure(String message) {
                resultMessage = message;
                resultPhotoLiveData.setValue(Result.FAILURE);
            }
        });
    }


    public void resetResult() {
        getInstance().resultLiveData.setValue(null);
    }
}
