package com.example.andrew.project_bordin_costa;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

import static android.content.ContentValues.TAG;

public class EmergencyActivity extends Activity {

    int arrayPosition;

    TextView txtDescription;
    TextView txtWebsite;
    TextView txtNumber;

    String description;
    String website;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        txtDescription = findViewById(R.id.txt_description);
        txtWebsite = findViewById(R.id.txt_website);
        txtNumber = findViewById(R.id.txt_number);

        description = "";
        website = "";
        number = "";

        Intent intent = getIntent();

        if(intent != null){
            arrayPosition = intent.getIntExtra("arrayPosition", 0);
            loadInfo(arrayPosition);
            setInfo();
        }
    }

    public void loadInfo(int position) {
        Resources res  = getResources();

        InputStream inputStream = res.openRawResource(R.raw.cities);
        Scanner scanner = new Scanner(inputStream);

        StringBuilder builder = new StringBuilder();

        while(scanner.hasNextLine())
        {
            builder.append(scanner.nextLine());
        }

        String jsonString = builder.toString();
        JSONObject city = getCity(jsonString, position);

        try {
            JSONObject emergency = city.getJSONObject("emergency");
            getInfo(emergency);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private JSONObject getCity(String jsonString, int position) {
        try {
            JSONObject root = new JSONObject(jsonString);
            JSONArray cities = root.getJSONArray("cities");
            JSONObject city = cities.getJSONObject(position);
            return city;

        } catch (JSONException e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }

    private void getInfo(JSONObject emergency) {
        try {
            description = emergency.getString("description");
            website = emergency.getString("website");
            number = emergency.getString("number");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setInfo() {
        txtDescription.setText(description);
        txtWebsite.setText(website);
        txtNumber.setText(number);
    }
}
