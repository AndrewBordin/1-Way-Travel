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
    int arrayPosition;

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

                Calendar cal = Calendar.getInstance();
                cal.set(datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        0,
                        00,
                        00);

                if(cal.compareTo(current) <= 0){
                    //The set Date/Time already passed
                    Toast.makeText(getApplicationContext(),
                            "Invalid Date/Time",
                            Toast.LENGTH_LONG).show();
                }else{
                    setAlarm(cal);
                }

            }
        });
    }

    private void setAlarm(Calendar targetCal){
        Toast.makeText(getApplicationContext(), "\n\n***\n"
                + "Alarm is set@ " + targetCal.getTime() + "\n"
                + "***\n", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
    }


}
