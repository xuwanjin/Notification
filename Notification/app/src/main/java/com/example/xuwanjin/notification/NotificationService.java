package com.example.xuwanjin.notification;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.widget.Toast;

/**
 * Created by xuwanjin on 6/5/17.
 */

public class NotificationService extends NotificationListenerService {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        Toast.makeText(this,"onNotificationRemoved", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        Toast.makeText(this,"onNotificationPosted", Toast.LENGTH_SHORT).show();
    }
}
