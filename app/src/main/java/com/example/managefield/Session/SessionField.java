package com.example.managefield.Session;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.managefield.Interface.CallBack;
import com.example.managefield.Interface.FieldChangeCallBack;
import com.example.managefield.data.enumeration.Result;
import com.example.managefield.data.repository.FieldRepository;
import com.example.managefield.model.Field;
import com.google.firebase.iid.FirebaseInstanceId;


import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

public class SessionField implements FieldChangeCallBack {
    private static SessionField instance;
    private Application application;
    private FieldRepository fieldRepository = FieldRepository.getInstance();
    private MutableLiveData<Field> filedLiveData = new MutableLiveData<>();
    private MutableLiveData<File> fieldAvatarLiveData= new MutableLiveData<>();
    private MutableLiveData<File> fieldCoverLiveData = new MutableLiveData<>();
    private MutableLiveData<Result> resultLiveData = new MutableLiveData<>(null);
    private MutableLiveData<Result> resultPhotoLiveData = new MutableLiveData<>(null);
    private String resultMessage = null;

    public static SessionField getInstance() {
        if (instance == null) {
            instance = new SessionField();
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
        return filedLiveData;
    }

    public void setPlayerLiveData(Field player){
        filedLiveData.setValue(player);
    }

    public LiveData<File>  getAvatarLiveData() {return  fieldAvatarLiveData ;};

    public LiveData<File>  getCoverLiveData() {return  fieldCoverLiveData ;};

    public MutableLiveData<Field> getFiledLiveData() {
        return filedLiveData;
    }

    @Override
    public void onUserChange(Field field) {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FirebaseInstanceId.getInstance().deleteInstanceId();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();



        filedLiveData.setValue(field);
        if (field != null) {
            if (!field.getUrlAvatar().isEmpty()) {
                String[] files = field.getUrlAvatar().split("/");
                String fileName = files[files.length-1];
                File photo = new File(getApplicationContext().getCacheDir(), fileName);
                // 24 hours
                if (photo.exists() && photo.lastModified() < Calendar.getInstance().getTimeInMillis() - 86400000){
                    fieldAvatarLiveData.setValue(photo);
                } else {
                    fieldRepository.getUserPhoto(new CallBack<File, String>() {
                        @Override
                        public void onSuccess(File file) {
                            fieldAvatarLiveData.setValue(file);
                        }

                        @Override
                        public void onFailure(String s) {

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
                    fieldCoverLiveData.setValue(photo);
                } else {
                    fieldRepository.getCoverPhoto(new CallBack<File, String>() {
                        @Override
                        public void onSuccess(File file) {
                            fieldCoverLiveData.setValue(file);
                        }

                        @Override
                        public void onFailure(String s) {

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
        fieldRepository.updateProfile(updateBasic, new CallBack<String, String>() {
            @Override
            public void onSuccess(String s) {
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
        fieldRepository.updateImage(uri, path, isAvatar, new CallBack<String, String>() {
            @Override
            public void onSuccess(String url) {
                Log.d("check updateUI", "onSuccess: VAO DAY R NE");
                resultPhotoLiveData.setValue(Result.SUCCESS);
                Field field = getInstance().filedLiveData.getValue();
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
