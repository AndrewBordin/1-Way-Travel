package com.example.andrew.project_bordin_costa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.andrew.project_bordin_costa.model.Destination;

import java.util.ArrayList;

public class DestinationActivity extends Activity {

    ArrayList<Destination> destinationArrayList;
    TextView txtCityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        txtCityName = findViewById(R.id.txtCityName);

        Intent intent = getIntent();

        if(intent != null)
        {
            destinationArrayList = (ArrayList<Destination>)intent.getSerializableExtra("data");

            int arrayPosition = intent.getIntExtra("arrayPosition", 0);

            txtCityName.setText(destinationArrayList.get(arrayPosition).getCityName());

        }
    }
}
