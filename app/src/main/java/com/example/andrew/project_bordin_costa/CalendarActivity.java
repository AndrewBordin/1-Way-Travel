package com.example.andrew.project_bordin_costa;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.andrew.project_bordin_costa.model.AlarmReceiver;

import java.util.Calendar;

public class CalendarActivity extends Activity {

    Button btnSaveDate;
    DatePicker datePicker;
    final static int RQS_1 = 1;
    final static int RQS_2 = 2;
    int arrayPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        btnSaveDate = findViewById(R.id.btnSaveDate);
        datePicker = findViewById(R.id.datePicker);

        Intent intent = getIntent();

        if(intent != null){
            arrayPosition = intent.getIntExtra("arrayPosition", 0);
        }


        btnSaveDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar current = Calendar.getInstance();
                Calendar notification = Calendar.getInstance();
                Calendar userPick = Calendar.getInstance();

                userPick.set(datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        0,
                        00,
                        00);

                notification.set(datePicker.getYear(),
                        datePicker.getMonth() - 1,
                        datePicker.getDayOfMonth(),
                        12,
                        00,
                        00);

                if (userPick.compareTo(current) <= 0){
                    //the date chosen is in the past
                    Toast.makeText(getApplicationContext(),
                            "Invalid Date. Date chosen has passed.",
                            Toast.LENGTH_LONG).show();
                }

                else {

                    if (notification.compareTo(current) <= 0) {
                        //The date chosen is less than a month away
                        setAlarm(current, RQS_2);
                    } else {
                        //The data chosen is more than a month away
                        setAlarm(notification, RQS_1);
                    }
                }

            }
        });
    }

    private void setAlarm(Calendar targetCal, int flag){

        if (flag == RQS_1){
            Toast.makeText(getApplicationContext(), "\n\n***\n"
                + "You will be notified a month before your vacation to book hotels and flight" + "\n"
                + "***\n", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
            intent.putExtra("arrayPosition", arrayPosition);
            intent.putExtra("notificationID", flag);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                    RQS_1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        }
        else {
            Toast.makeText(getApplicationContext(),
                    "\n\n***\n" +
                            "Your vacation is in less than a month! Click notification to go to expedia.ca"
                            + "\n"
                            + "***\n",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
            intent.putExtra("arrayPosition", arrayPosition);
            intent.putExtra("notificationID", flag);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                    RQS_2, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        }
    }


}
