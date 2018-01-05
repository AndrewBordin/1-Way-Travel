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
import android.widget.Toast;

import com.example.andrew.project_bordin_costa.model.AlarmReceiver;

import java.util.Calendar;

//note: alarm setting and date picker code snippets from
//http://android-er.blogspot.ca/2013/06/set-alarm-on-specified-datetime-with.html
public class CalendarActivity extends Activity {

    //variable declarations

    //variable which receives the city chosen
    int arrayPosition;

    Button btnSaveDate;
    DatePicker datePicker;

    //Constants to know which notification to send
    final static int RQS_1 = 1;
    final static int RQS_2 = 2;

    //constant for the notification month which is always on month before the date the user chooses
    final static int NOTIFICATION_MONTH = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //Variable assignment
        btnSaveDate = findViewById(R.id.btnSaveDate);
        datePicker = findViewById(R.id.datePicker);

        //get the intent
        Intent intent = getIntent();

        if(intent != null){
            //from the intent get the city chosen
            arrayPosition = intent.getIntExtra("arrayPosition", 0);
        }

        //Onclick listener for when the user wants to save a tentative date to travel
        btnSaveDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Create 3 calendar objects with the current date and time
                Calendar current = Calendar.getInstance();
                Calendar notification = Calendar.getInstance();
                Calendar userPick = Calendar.getInstance();

                //set the userPick calendar object to the date the user chose in the calendar
                userPick.set(datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        0,
                        0,
                        0);

                //set the notification calendar object to the date the user chose minus 1 month
                //since the notification for the trip will be given one month ahead at noon
                notification.set(datePicker.getYear(),
                        datePicker.getMonth() - NOTIFICATION_MONTH,
                        datePicker.getDayOfMonth(),
                        12,
                        0,
                        0);

                //if the user picked a date that is the current date or in the past
                //create a toast relying such information
                if (userPick.compareTo(current) <= 0){
                    Toast.makeText(getApplicationContext(),
                            R.string.invalid_date_toaster,
                            Toast.LENGTH_LONG).show();
                }

                else {

                    //if the user picked a date that is less than a month away
                    //set the alarm with the appropriate flag
                    if (notification.compareTo(current) <= 0) {
                        setAlarmToast(current, RQS_2);
                    } else {
                        //The date chosen is more than a month away
                        //and set the alarm with the appropriate flag
                        setAlarmToast(notification, RQS_1);
                    }
                }

            }
        });
    }

    //Make the appropriate toast depending on the user date choice
    //and send the alarm for the AlarmReceiver POJO
    private void setAlarmToast(Calendar targetCal, int flag) {

        makeToast(flag);
        sendAlarm(targetCal, flag);
    }

    //notify the user when the notification will appear on the phone (either a month before the date
    //chosen or right away/after ~2 seconds)
    private void makeToast(int flag) {
        if (flag == RQS_1){
            Toast.makeText(getApplicationContext(), "\n\n***\n"
                    + getString(R.string.toast_vacation_more_than_a_month) + "\n\n"
                    + "***\n", Toast.LENGTH_LONG).show();
        }

        else {
            Toast.makeText(getApplicationContext(),
                    "\n\n***\n" +
                            getString(R.string.toast_vacation_less_than_a_month)
                            + "\n\n"
                            + "***\n",
                    Toast.LENGTH_LONG).show();

        }
    }

    //send the alarm at the appropriate date and time (in ms) for the alarm receiver POJO
    private void sendAlarm(Calendar targetCal, int flag) {

        //create the intent which will go to AlarmReceiver
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);

        //add as extra the city chosen and which notification message to show dependent on the
        //notificationID
        intent.putExtra("arrayPosition", arrayPosition);
        intent.putExtra("notificationID", flag);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                flag, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        //set the alarm for the date and time chosen (either right now or a month before date user
        //chose)
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);

    }

}
