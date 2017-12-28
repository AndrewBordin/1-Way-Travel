package com.example.andrew.project_bordin_costa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.andrew.project_bordin_costa.model.Destination;

import java.util.ArrayList;

public class DestinationActivity extends Activity {

    ArrayList<Destination> destinationArrayList;
    Button btnDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        btnDatePicker = findViewById(R.id.btnDateButton);

        Intent intent = getIntent();

        if(intent != null)
        {
            destinationArrayList = (ArrayList<Destination>)intent.getSerializableExtra("data");

            int arrayPosition = intent.getIntExtra("arrayPosition", 0);

            btnDatePicker.setText("\n\n\n\n\n" + destinationArrayList.get(arrayPosition).getCityName() + ": Pick a Travel Date");

        }
    }
}
