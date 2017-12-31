package com.example.andrew.project_bordin_costa;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
    FloatingActionButton fabDate;
    ImageView imageCity;
    TextView txtDestinationName;
    TextView txtDestinationSubheading;
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

        fabDate = findViewById(R.id.fabDate);
        fabDate.setOnClickListener(this);

        txtDestinationName = findViewById(R.id.txtDestName);

        txtDestinationSubheading = findViewById(R.id.txtSubheading);

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

            String citySubheading = destinationArrayList.get(arrayPosition).getCitySubheading();

            txtDestinationName.setText(cityname);

            txtDestinationSubheading.setText(citySubheading);

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

            case R.id.fabDate:
                //code
                intent = new Intent(getApplicationContext(),CalendarActivity.class);
                intent.putExtra("arrayPosition", arrayPosition);
                startActivityForResult(intent, CALENDAR_ACTIVITY);
                break;
        }
    }
}
