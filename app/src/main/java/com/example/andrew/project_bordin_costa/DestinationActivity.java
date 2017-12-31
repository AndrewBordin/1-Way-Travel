package com.example.andrew.project_bordin_costa;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andrew.project_bordin_costa.model.Destination;

import java.util.ArrayList;

public class DestinationActivity extends Activity implements View.OnClickListener{

    ArrayList<Destination> destinationArrayList;
    Button btnDatePicker;
    Button btnThingsToDo;
    Button btnRestaurants;
    Button btnMap;
    Button btnEmergency;
    ImageView imageCity;
    TextView txtDestinationName;
    private static final int THINGS_TO_DO_ACTIVITY = 3;
    private static final int RESTAURANT_ACTIVITY = 4;
    private static final int MAP_ACTIVITY = 5;
    private static final int EMERGENCY_ACTIVITY = 6;
    private static final int CALENDAR_ACTIVITY = 7;
    int arrayPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        btnDatePicker = findViewById(R.id.btnDateButton);
        btnDatePicker.setOnClickListener(this);

        txtDestinationName = findViewById(R.id.txtDestName);

        imageCity = findViewById(R.id.imageCity);

        btnThingsToDo = findViewById(R.id.btnThingstodo);
        btnThingsToDo.setOnClickListener(this);

        btnRestaurants = findViewById(R.id.btnRestaurants);
        btnRestaurants.setOnClickListener(this);

        btnMap = findViewById(R.id.btnMap);
        btnMap.setOnClickListener(this);

        btnEmergency = findViewById(R.id.btnEmergency);
        btnEmergency.setOnClickListener(this);

        Intent intent = getIntent();

        if(intent != null)
        {
            destinationArrayList = (ArrayList<Destination>)intent.getSerializableExtra("data");

            arrayPosition = intent.getIntExtra("arrayPosition", 0);

            String cityname = destinationArrayList.get(arrayPosition).getCityName();

            txtDestinationName.setText(cityname);

            imageCity.setImageResource(destinationArrayList.get(arrayPosition).getCityPicture());
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()){
            case R.id.btnThingstodo:
                //code
                intent = new Intent(getApplicationContext(),ThingsToDoActivity.class);
                intent.putExtra("arrayPosition", arrayPosition);
                startActivityForResult(intent, THINGS_TO_DO_ACTIVITY);
                break;

            case R.id.btnRestaurants:
                //code
                intent = new Intent(getApplicationContext(),RestaurantActivity.class);
                intent.putExtra("arrayPosition", arrayPosition);
                startActivityForResult(intent, RESTAURANT_ACTIVITY);
                break;

            case R.id.btnMap:
                //code
                intent = new Intent(getApplicationContext(),MapActivity.class);
                intent.putExtra("arrayPosition", arrayPosition);
                startActivityForResult(intent, MAP_ACTIVITY);
                break;

            case R.id.btnEmergency:
                //code
                intent = new Intent(getApplicationContext(),EmergencyActivity.class);
                intent.putExtra("arrayPosition", arrayPosition);
                startActivityForResult(intent, EMERGENCY_ACTIVITY);
                break;

            case R.id.btnDateButton:
                //code
                intent = new Intent(getApplicationContext(),CalendarActivity.class);
                intent.putExtra("arrayPosition", arrayPosition);
                startActivityForResult(intent, CALENDAR_ACTIVITY);
                break;
        }
    }
}
