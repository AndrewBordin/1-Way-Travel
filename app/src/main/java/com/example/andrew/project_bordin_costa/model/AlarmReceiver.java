package com.example.andrew.project_bordin_costa.model;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.example.andrew.project_bordin_costa.R;

public class AlarmReceiver extends BroadcastReceiver {


    //the arrayPosition value for each city
    final int TORONTO = 0;
    final int LONDON = 1;
    final int SANFRAN = 2;
    final int SYDNEY = 3;

    NotificationManager notificationManager;

    //id for the notification to be created
    private static final int NOTIFICATION_ID = 1;
    private static final int OVER_ONE_MONTH = 1;

    @Override
    public void onReceive(Context context, Intent arg1) {

        //variable which receives the city chosen
        int arrayPosition;

        ////variable which receives the notification to be used
        int notificationID;

        arrayPosition = arg1.getIntExtra("arrayPosition", 0);
        notificationID = arg1.getIntExtra("notificationID", 1);


        //URL of expedia.ca specific to each city, which will open in the browser once the user
        //clicks notification
        String urlToronto = "https://www.expedia.ca/Hotel-Search?destination=Toronto%2C+" +
                "Ontario%2C+Canada&latLong=43.648400%2C-79.382170&regionId=178314&startDate=&endDate=&rooms=" +
                "1&_xpid=11905%7C1&adults=2";

        String urlLondon = "https://www.expedia.ca/Hotel-Search?destination=London%2C+" +
                "England%2C+United+Kingdom&latLong=51.507538%2C-0.127804&regionId=178279&startDate=" +
                "&endDate=&rooms=1&_xpid=11905%7C1&adults=2";

        String urlSydney = "https://www.expedia.ca/Hotel-Search?destination=Sydney%2C+New" +
                "+South+Wales%2C+Australia&latLong=-33.867570%2C151.208440&regionId=178312&startDate=" +
                "&endDate=&rooms=1&_xpid=11905%7C1&adults=2";

        String urlSanFrancisco = "https://www.expedia.ca/Hotel-Search?destination=" +
                "San+Francisco%2C+California%2C+United+States+Of+America&latLong=37.787400%2C-122.408200" +
                "&regionId=178305&startDate=&endDate=&rooms=1&_xpid=11905%7C1&adults=2";

        //when the id is for the notification that will be sent one month before the date the user
        //chose, have the title of the notification be "Vacation to {city} is in a month!"
        if (notificationID == OVER_ONE_MONTH) {
            switch (arrayPosition) {
                case TORONTO:

                    //create the intent to view the URL in the browser
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlToronto));

                    PendingIntent pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            myIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    NotificationCompat.Builder myNotification = new NotificationCompat.Builder(context, "My_channel_id")
                            .setContentTitle("Vacation to Toronto is in a month!")
                            .setContentText("Click here to book hotel and flight")
                            .setTicker("Notification!")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.airplaneicon);

                    //create the notification
                    notificationManager =
                            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFICATION_ID, myNotification.build());
                    break;

                case LONDON:

                    myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlLondon));

                    pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            myIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    myNotification = new NotificationCompat.Builder(context, "My_channel_id")
                            .setContentTitle("Vacation to London is in a month!")
                            .setContentText("Click here to book hotel and flight")
                            .setTicker("Notification!")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.airplaneicon);

                    //create the notification
                    notificationManager =
                            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFICATION_ID, myNotification.build());
                    break;

                case SANFRAN:

                    myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlSanFrancisco));

                    pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            myIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    myNotification = new NotificationCompat.Builder(context, "My_channel_id")
                            .setContentTitle("Vacation to San Francisco is in a month!")
                            .setContentText("Click here to book hotel and flight")
                            .setTicker("Notification!")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.airplaneicon);

                    //create the notification
                    notificationManager =
                            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFICATION_ID, myNotification.build());
                    break;

                case SYDNEY:

                    myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlSydney));

                    pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            myIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    myNotification = new NotificationCompat.Builder(context, "My_channel_id")
                            .setContentTitle("Vacation to Sydney is in a month!")
                            .setContentText("Click here to book hotel and flight")
                            .setTicker("Notification!")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.airplaneicon);

                    //create the notification
                    notificationManager =
                            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFICATION_ID, myNotification.build());
                    break;
            }
        }

        //when the id is for the notification that will be sent RIGHT NOW
        //have the title of the notification be "Vacation to {city} is in less than a month!"
        else {
            switch (arrayPosition) {
                case TORONTO:

                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlToronto));

                    PendingIntent pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            myIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    NotificationCompat.Builder myNotification = new NotificationCompat.Builder(context, "My_channel_id")
                            .setContentTitle("Vacation to Toronto is in less than a month!")
                            .setContentText("Click here to book hotel and flight")
                            .setTicker("Notification!")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.airplaneicon)
                            .setColor(Color.LTGRAY);

                    //create the notification
                    notificationManager =
                            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFICATION_ID, myNotification.build());
                    break;

                case LONDON:

                    myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlLondon));

                    pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            myIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    myNotification = new NotificationCompat.Builder(context, "My_channel_id")
                            .setContentTitle("Vacation to London is in less than a month!")
                            .setContentText("Click here to book hotel and flight")
                            .setTicker("Notification!")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.airplaneicon);

                    //create the notification
                    notificationManager =
                            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFICATION_ID, myNotification.build());
                    break;

                case SANFRAN:

                    myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlSanFrancisco));

                    pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            myIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    myNotification = new NotificationCompat.Builder(context, "My_channel_id")
                            .setContentTitle("Vacation to San Francisco is in less than a month!")
                            .setContentText("Click here to book hotel and flight")
                            .setTicker("Notification!")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.airplaneicon);

                    //create the notification
                    notificationManager =
                            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFICATION_ID, myNotification.build());
                    break;

                case SYDNEY:

                    myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlSydney));

                    pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            myIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    myNotification = new NotificationCompat.Builder(context, "My_channel_id")
                            .setContentTitle("Vacation to Sydney is in less than a month!")
                            .setContentText("Click here to book hotel and flight")
                            .setTicker("Notification!")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.airplaneicon);

                    //create the notification
                    notificationManager =
                            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFICATION_ID, myNotification.build());
                    break;
            }
        }

    }

}
