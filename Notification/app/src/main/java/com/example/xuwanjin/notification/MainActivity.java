package com.example.xuwanjin.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button notification_listener_service;
    Button notification_normal;
    Button notification_inline_replay;
    Button notification_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent1 = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        startActivity(intent1);
        notification_listener_service = (Button) findViewById(R.id.notification_listener_service);
        notification_listener_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotificationService.class);
                startService(intent);
            }
        });

        notification_normal = (Button) findViewById(R.id.notification_normal);
        notification_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(android.R.drawable.stat_notify_sdcard_usb)
                        .setContentText((new Random()).nextInt() + "XXXXX")
                        .setContentTitle("Always New Notification")
                        .setDefaults(NotificationCompat.DEFAULT_LIGHTS)
                        .setWhen(0)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .setLocalOnly(true);
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(notification.hashCode(), notification.build());
            }
        });


        notification_update = (Button) findViewById(R.id.notification_update);
        notification_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder updateNotification = new NotificationCompat.Builder(getApplicationContext());
                updateNotification.setContentTitle("Update Notification")
                        .setSmallIcon(android.R.drawable.ic_popup_reminder)
                        .setContentText(new Random().nextInt() + "YYYY")
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .setWhen(0)
                        .setLocalOnly(true);

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(111, updateNotification.build());
            }
        });

        notification_inline_replay = (Button) findViewById(R.id.inline_reply_notification);
        notification_inline_replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.addCategory(Intent.ACTION_DEFAULT);
                PendingIntent pendingIntent =
                        PendingIntent.getService(getBaseContext(), 99, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                RemoteInput remoteInput = new RemoteInput.Builder("remote_ket_text").setLabel("inline reply notification hint").build();
                NotificationCompat.Action action = new NotificationCompat.Action.Builder(
                        android.R.drawable.screen_background_dark, "Action Title", pendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();

                NotificationCompat.Builder
                        inLineReplyNotification = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(android.R.drawable.ic_notification_clear_all)
                        .setContentTitle("Inline Reply Notification Content Title")
                        .setContentText((new Random()).nextInt() + " Notification Content")
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setWhen(0)
                        .addAction(action)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(111, inLineReplyNotification.build());
            }
        });
    }
}
