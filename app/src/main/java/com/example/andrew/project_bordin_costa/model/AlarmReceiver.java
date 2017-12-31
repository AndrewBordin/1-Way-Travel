package com.example.andrew.project_bordin_costa.model;

/**
 * Created by Andrew on 12/30/2017.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.andrew.project_bordin_costa.R;

public class AlarmReceiver extends BroadcastReceiver {

    private static final int MY_NOTIFICATION_ID=1;
    NotificationManager notificationManager;
    Notification myNotification;

    private final String Url = "https://www.expedia.ca";

    @Override
    public void onReceive(Context context, Intent arg1) {

        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Url));

        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                myIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        myNotification = new NotificationCompat.Builder(context)
                .setContentTitle("Vacation Time!")
                .setContentText("You have chosen to travel in exactly one month. Have you booked your vacation" +
                        "at https://www.expedia.ca/ yet?")
                .setTicker("Notification!")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .build();

        notificationManager =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(MY_NOTIFICATION_ID, myNotification);

    }

}
