package com.example.andrew.project_bordin_costa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.andrew.project_bordin_costa.model.Destination;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener{

    //Variable Declarations
    Button btnToronto;
    Button btnLondon;
    Button btnSanFran;
    Button btnSydney;
    ArrayList<Destination> destinations;
    int currentDestination = 0;
    private static final int DESTINATION_ACTIVITY = 2;

    //Run when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting the button variables to their corresponding buttons
        //Setting each button to an onclicklistener

        //NOTE* Instead of assigning each button to their individual onclicklistener, we decided
        //to override the onclick method, and use a switch statement to account for all the button clicks
        btnToronto = findViewById(R.id.btnToronto);
        btnToronto.setOnClickListener(this);

        btnLondon = findViewById(R.id.btnLondon);
        btnLondon.setOnClickListener(this);

        btnSanFran = findViewById(R.id.btnSanFran);
        btnSanFran.setOnClickListener(this);

        btnSydney = findViewById(R.id.btnSydney);
        btnSydney.setOnClickListener(this);

        //Calling the getDestination method from the Destination POJO which creates an array with
        //all of the destinations and information associated with them
        destinations = Destination.getDestination();
    }

    //Overriden onclick method
    @Override
    public void onClick(View view) {

        //Creating an intent which gets the application context and destination activity
        Intent intent = new Intent(getApplicationContext(),DestinationActivity.class);
        //Giving the intent the destinations array
        intent.putExtra("data", destinations);

        //switch that gets the id of the button click, runs the corresponding code and activity.
        switch(view.getId()){

            case R.id.btnToronto:
                //Setting the current destination
                //giving the intent the current destination. The destination that was chosen.
                currentDestination = 0;
                intent.putExtra("arrayPosition", currentDestination);
                startActivityForResult(intent, DESTINATION_ACTIVITY);
                break;

            case R.id.btnLondon:
                currentDestination = 1;
                intent.putExtra("arrayPosition", currentDestination);
                startActivityForResult(intent, DESTINATION_ACTIVITY);
                break;

            case R.id.btnSanFran:
                currentDestination = 2;
                intent.putExtra("arrayPosition", currentDestination);
                startActivityForResult(intent, DESTINATION_ACTIVITY);
                break;

            case R.id.btnSydney:
                currentDestination = 3;
                intent.putExtra("arrayPosition", currentDestination);
                startActivityForResult(intent, DESTINATION_ACTIVITY);
                break;

            default:
                //If no choice is made but the onclick still runs, launch a toast to the user.
                Toast.makeText(getApplicationContext(), "Error: No destination chosen.", Toast.LENGTH_LONG).show();
        }

    }
}
