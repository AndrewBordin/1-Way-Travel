package com.example.andrew.project_bordin_costa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.andrew.project_bordin_costa.model.Destination;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener{

    Button btnToronto;
    Button btnLondon;
    Button btnSanFran;
    Button btnSydney;
    ArrayList<Destination> destinations;

    private static final int DESTINATION_ACTIVITY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToronto = findViewById(R.id.btnToronto);
        btnToronto.setOnClickListener(this);

        btnLondon = findViewById(R.id.btnLondon);
        btnLondon.setOnClickListener(this);

        btnSanFran = findViewById(R.id.btnSanFran);
        btnSanFran.setOnClickListener(this);

        btnSydney = findViewById(R.id.btnSydney);
        btnSydney.setOnClickListener(this);

        destinations = Destination.getDestination();
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getApplicationContext(),DestinationActivity.class);
        int currentDestination = 0;
        intent.putExtra("data", destinations);

        switch(view.getId()){

            case R.id.btnToronto:
                //code
                currentDestination = 0;
                break;

            case R.id.btnLondon:
                //code
                currentDestination = 1;
                break;

            case R.id.btnSanFran:
                //code
                currentDestination = 2;
                break;

            case R.id.btnSydney:
                //code
                currentDestination = 3;
                break;
        }
        intent.putExtra("arrayPosition", currentDestination);
        startActivityForResult(intent, DESTINATION_ACTIVITY);

    }
}
