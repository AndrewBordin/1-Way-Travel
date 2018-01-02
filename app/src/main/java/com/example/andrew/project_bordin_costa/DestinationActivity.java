package com.example.andrew.project_bordin_costa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andrew.project_bordin_costa.model.Destination;

import java.util.ArrayList;

public class DestinationActivity extends Activity implements View.OnClickListener{

    //Variable Declarations
    ArrayList<Destination> destinationArrayList;
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
        //Setting the button variables to their corresponding buttons
        //Setting each button to an onclicklistener
        fabDate = findViewById(R.id.fabDate);
        fabDate.setOnClickListener(this);

        btnThingsToDo = findViewById(R.id.btnThingstodo);
        btnThingsToDo.setOnClickListener(this);

        btnRestaurants = findViewById(R.id.btnRestaurants);
        btnRestaurants.setOnClickListener(this);

        btnMap = findViewById(R.id.btnMap);
        btnMap.setOnClickListener(this);

        btnEmergency = findViewById(R.id.btnEmergency);
        btnEmergency.setOnClickListener(this);

        //Setting the textview variables to their corresponding textview
        txtDestinationName = findViewById(R.id.txtDestName);
        txtDestinationSubheading = findViewById(R.id.txtSubheading);

        //Setting the imageview variable for the image of the city clicked to it's corresponding-
        //imageview
        imageCity = findViewById(R.id.imageCity);

        //Getting the intent from the previous activity(MainActivity)
        Intent intent = getIntent();

        //do if the intent has contents
        if(intent != null)
        {
            //Getting the arraylist from the intent
            //Since we have complete control over the array, the warning can be disregarded
            destinationArrayList = (ArrayList<Destination>)intent.getSerializableExtra("data");

            //getting the arrayposition(Cityclicked) from the intent
            arrayPosition = intent.getIntExtra("arrayPosition", 0);

            //Getting the name of the city from the destination pojo and setting it to a String
            String cityname = destinationArrayList.get(arrayPosition).getCityName();

            //Getting the city subheading from the destination pojo and setting it to a String.
            String citySubheading = destinationArrayList.get(arrayPosition).getCitySubheading();

            //Setting both the city name and subheading to Textviews.
            txtDestinationName.setText(cityname);

            txtDestinationSubheading.setText(citySubheading);

            //Getting the drawable image from the destination POJO associated with the city and setting
            //it to the imageCity imageview.
            imageCity.setImageResource(destinationArrayList.get(arrayPosition).getCityPicture());
        }
    }

    //Overriden onclick method handling each buttonclick on the activity
    @Override
    public void onClick(View view) {

        //Creating an intent which gets the application context and the chosen activity
        Intent intent;

        //switch that gets the id of the button click, runs the corresponding code and activity.
        switch(view.getId()){
            case R.id.btnThingstodo:
                intent = new Intent(getApplicationContext(),ThingsToDoActivity.class);
                //giving the intent the current destination. The destination that was chosen.
                intent.putExtra("arrayPosition", arrayPosition);
                //Launching the activity
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
