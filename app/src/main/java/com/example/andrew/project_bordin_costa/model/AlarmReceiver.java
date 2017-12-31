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
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.andrew.project_bordin_costa.R;

public class AlarmReceiver extends BroadcastReceiver {

    private static final int MY_NOTIFICATION_ID=1;
    NotificationManager notificationManager;
    Notification myNotification;

    private final String UrlToronto = "https://www.expedia.ca/Hotel-Search?destination=Toronto%2C+" +
            "Ontario%2C+Canada&latLong=43.648400%2C-79.382170&regionId=178314&startDate=&endDate=&rooms=" +
            "1&_xpid=11905%7C1&adults=2";

    private final String UrlLondon = "https://www.expedia.ca/Hotel-Search?destination=London%2C+" +
            "England%2C+United+Kingdom&latLong=51.507538%2C-0.127804&regionId=178279&startDate=" +
            "&endDate=&rooms=1&_xpid=11905%7C1&adults=2";

    private final String UrlSydney = "https://www.expedia.ca/Hotel-Search?destination=Sydney%2C+New" +
            "+South+Wales%2C+Australia&latLong=-33.867570%2C151.208440&regionId=178312&startDate=" +
            "&endDate=&rooms=1&_xpid=11905%7C1&adults=2";

    private final String UrlSanFrancisco = "https://www.expedia.ca/Hotel-Search?destination=" +
            "San+Francisco%2C+California%2C+United+States+Of+America&latLong=37.787400%2C-122.408200" +
            "&regionId=178305&startDate=&endDate=&rooms=1&_xpid=11905%7C1&adults=2";

    @Override
    public void onReceive(Context context, Intent arg1) {

        int arrayPosition = 0;
        int notificationID = 1;

        arrayPosition = arg1.getIntExtra("arrayPosition", 0);
        notificationID = arg1.getIntExtra("notificationID", 1);

        if (notificationID == 1) {
            switch (arrayPosition) {
                case 0:

                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UrlToronto));

                    PendingIntent pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            myIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    myNotification = new NotificationCompat.Builder(context)
                            .setContentTitle("Vacation to Toronto is in a month!")
                            .setContentText("Click here to book hotel and flight")
                            .setTicker("Notification!")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.airplaneicon)
                            .setColor(Color.LTGRAY)
                            .build();
                    break;

                case 1:

                    myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UrlLondon));

                    pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            myIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    myNotification = new NotificationCompat.Builder(context)
                            .setContentTitle("Vacation to London is in a month!")
                            .setContentText("Click here to book hotel and flight")
                            .setTicker("Notification!")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.airplaneicon)
                            .build();
                    break;

                case 2:

                    myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UrlSanFrancisco));

                    pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            myIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    myNotification = new NotificationCompat.Builder(context)
                            .setContentTitle("Vacation to San Francisco is in a month!")
                            .setContentText("Click here to book hotel and flight")
                            .setTicker("Notification!")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.airplaneicon)
                            .build();
                    break;

                case 3:

                    myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UrlSydney));

                    pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            myIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    myNotification = new NotificationCompat.Builder(context)
                            .setContentTitle("Vacation to Sydney is in a month!")
                            .setContentText("Click here to book hotel and flight")
                            .setTicker("Notification!")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.airplaneicon)
                            .build();
                    break;
            }

            notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
        }

        else {
            switch (arrayPosition) {
                case 0:

                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UrlToronto));

                    PendingIntent pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            myIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    myNotification = new NotificationCompat.Builder(context)
                            .setContentTitle("Vacation to Toronto is in less than a month!")
                            .setContentText("Click here to book hotel and flight")
                            .setTicker("Notification!")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.airplaneicon)
                            .setColor(Color.LTGRAY)
                            .build();
                    break;

                case 1:

                    myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UrlLondon));

                    pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            myIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    myNotification = new NotificationCompat.Builder(context)
                            .setContentTitle("Vacation to London is in less than a month!")
                            .setContentText("Click here to book hotel and flight")
                            .setTicker("Notification!")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.airplaneicon)
                            .build();
                    break;

                case 2:

                    myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UrlSanFrancisco));

                    pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            myIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    myNotification = new NotificationCompat.Builder(context)
                            .setContentTitle("Vacation to San Francisco is in less than a month!")
                            .setContentText("Click here to book hotel and flight")
                            .setTicker("Notification!")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.airplaneicon)
                            .build();
                    break;

                case 3:

                    myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UrlSydney));

                    pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            myIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    myNotification = new NotificationCompat.Builder(context)
                            .setContentTitle("Vacation to Sydney is in less than a month!")
                            .setContentText("Click here to book hotel and flight")
                            .setTicker("Notification!")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.airplaneicon)
                            .build();
                    break;
            }

            notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
        }

    }

}
