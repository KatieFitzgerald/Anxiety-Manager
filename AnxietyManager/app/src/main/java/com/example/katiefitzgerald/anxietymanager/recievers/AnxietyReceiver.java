package com.example.katiefitzgerald.anxietymanager.recievers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.katiefitzgerald.anxietymanager.R;
import com.example.katiefitzgerald.anxietymanager.activities.LoginActivity;
import com.example.katiefitzgerald.anxietymanager.activities.WhatsUpActivity;

/**
 * Created by Katie Fitzgerald on 02/04/2018.
 */

public class AnxietyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent notificationIntent = new Intent(context, LoginActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(LoginActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(100, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(context);

        Notification notification = builder.setContentTitle("Everything is okay - Anxiety Manager")
                .setContentText("Want to track what you're feeling?")
                .setTicker("New Message Alert!")
                .setSmallIcon(R.drawable.launcher)
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }

}


