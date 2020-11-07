package com.example.managefield.messagingService;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.managefield.Interface.CallBack;
import com.example.managefield.R;
import com.example.managefield.data.repository.FieldRepository;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private FieldRepository fieldRepository = FieldRepository.getInstance();

    @Override
    public void onCreate() {
        // check if registration is sent to server
    }

    @Override
    public void onNewToken(String token) {
        sendRegistrationTokenToServer(token);
    }

    private void sendRegistrationTokenToServer(String token) {
        Map<String, Object> updateProfileMap = new HashMap<>();
        updateProfileMap.put("registrationToken", token);
        fieldRepository.updateProfile(updateProfileMap, new CallBack<String, String>() {
            @Override
            public void onSuccess(String sucess) {
                Toast.makeText(getApplicationContext(), "Updated token", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        showNotification(remoteMessage.getData());
    }

    private void showNotification(Map<String, String> data) {
        String messageType = data.get("messageType");
        String contentText = "";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "example.myapplication.service.test";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription("MyClub");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        if (messageType!=null && messageType.equals("NewBooking")){
            String idTeam = data.get("playerId");
            String nameTeam = data.get("playerName");
             contentText = nameTeam + " just sent a join request";

        }

        //send notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
            notificationBuilder.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_clear_black_24dp)
                    .setContentTitle(messageType)
                    .setContentText(contentText)
                    .setContentInfo("Info");

            notificationManager.notify(new Random().nextInt(), notificationBuilder.build());
        }
    }
}
