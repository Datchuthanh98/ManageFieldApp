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
import com.example.managefield.data.repository.FieldRepository;
import com.example.managefield.model.Field;


import java.io.File;
import java.util.Calendar;
import java.util.Map;

public class FieldViewModel extends ViewModel implements UserChangeCallBack {
    private static FieldViewModel instance;
    private Application application;
    private FieldRepository fieldRepository = FieldRepository.getInstance();
    private MutableLiveData<Field> playerLiveData = new MutableLiveData<>();
    private MutableLiveData<File> playerAvatarLiveData= new MutableLiveData<>();
    private MutableLiveData<File> playerCoverLiveData = new MutableLiveData<>();
    private MutableLiveData<Result> resultLiveData = new MutableLiveData<>(null);
    private MutableLiveData<Result> resultPhotoLiveData = new MutableLiveData<>(null);
    private String resultMessage = null;

    public static FieldViewModel getInstance() {
        if (instance == null) {
            instance = new FieldViewModel();
        }
        return instance;
    }

    public Context getApplicationContext(){
        return application.getApplicationContext();
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public LiveData<Field> getPlayerLiveData() {
        return playerLiveData;
    }

    public void setPlayerLiveData(Field player){
        playerLiveData.setValue(player);
    }

    public LiveData<File>  getAvatarLiveData() {return  playerAvatarLiveData ;};

    public LiveData<File>  getCoverLiveData() {return  playerCoverLiveData ;};


    @Override
    public void onUserChange(Field field) {
        playerLiveData.setValue(field);
        if (field != null) {
            if (!field.getUrlAvatar().isEmpty()) {
                String[] files = field.getUrlAvatar().split("/");
                String fileName = files[files.length-1];
                File photo = new File(getApplicationContext().getCacheDir(), fileName);
                // 24 hours
                if (photo.exists() && photo.lastModified() < Calendar.getInstance().getTimeInMillis() - 86400000){
                    playerAvatarLiveData.setValue(photo);
                } else {
                    fieldRepository.getUserPhoto(new GetUserPhotoCallBack() {
                        @Override
                        public void onGetUserPhotoCallBack(File photo) {
                            playerAvatarLiveData.setValue(photo);
                        }
                    }, field.getUrlAvatar(), getApplicationContext());
                }
            }

            if (!field.getUrlCover().isEmpty()) {
                String[] files = field.getUrlCover().split("/");
                String fileName = files[files.length-1];
                File photo = new File(getApplicationContext().getCacheDir(), fileName);
                // 24 hours
                if (photo.exists() && photo.lastModified() < Calendar.getInstance().getTimeInMillis() - 86400000){
                    playerCoverLiveData.setValue(photo);
                } else {
                    fieldRepository.getCoverPhoto(new GetUserCoverCallBack() {
                        @Override
                        public void onGetUserCoverCallBack(File photo) {
                            playerCoverLiveData.setValue(photo);
                        }
                    }, field.getUrlCover(), getApplicationContext());
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
        fieldRepository.updateProfile(updateBasic, new UpdateProfileCallBack() {
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
        fieldRepository.updateImage(uri, path, isAvatar, new UpdateImageCallBack() {
            @Override
            public void onSuccess(String url) {
                Log.d("check updateUI", "onSuccess: VAO DAY R NE");
                resultPhotoLiveData.setValue(Result.SUCCESS);
                Field field = getInstance().playerLiveData.getValue();
                if (isAvatar){
                    field.setUrlAvatar(url);
                } else {
                    field.setUrlCover(url);
                }
                getInstance().onUserChange(field);
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
