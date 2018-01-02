package com.example.andrew.project_bordin_costa;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

import static android.content.ContentValues.TAG;

public class EmergencyActivity extends Activity {

    //variable declarations

    //variable which receives the city chosen
    int arrayPosition;

    //UI variables
    TextView txtDescription;
    TextView txtWebsite;
    Button btnCall;

    //variables which receive the information from json
    String description;
    String website;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        //Variable assignment
        txtDescription = findViewById(R.id.txt_description);
        txtWebsite = findViewById(R.id.txt_website);
        btnCall = findViewById(R.id.btnCall);

        description = "";
        website = "";
        number = "";

        //get the intent
        Intent intent = getIntent();

        if(intent != null){
            //from the intent get the city chosen, load its info then set the info to the UI variables
            arrayPosition = intent.getIntExtra("arrayPosition", 0);
            loadInfo(arrayPosition);
            setInfo();
        }

        //on click listener for btnCall which will open the phone's dialing pad with the appropriate
        //emergency number
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialNumber(number);
            }
        });
    }

    private void dialNumber(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    //Method to get info from the json file in raw
    public void loadInfo(int position) {
        Resources res  = getResources();

        //open the cities.json
        InputStream inputStream = res.openRawResource(R.raw.cities);
        Scanner scanner = new Scanner(inputStream);

        //create the string builder that will hold the json's contents
        StringBuilder builder = new StringBuilder();

        //scan every line and append it to the builder
        while(scanner.hasNextLine())
        {
            builder.append(scanner.nextLine());
        }

        String jsonString = builder.toString();

        //get the city object in the json from position (arrayPosition) user chose
        JSONObject city = getCity(jsonString, position);

        try {
            //get the proper emergency object accordingly to the city chosen
            JSONObject emergency = city.getJSONObject("emergency");
            //then get the city's emergency information
            getInfo(emergency);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //Method which gets the json array of all cities and gets the object of the one city the user
    //chose
    private JSONObject getCity(String jsonString, int position) {
        try {
            JSONObject root = new JSONObject(jsonString);
            JSONArray cities = root.getJSONArray("cities");
            return cities.getJSONObject(position);

        } catch (JSONException e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }

    //Get emergency information, assigning the info to the
    //appropriate variables
    private void getInfo(JSONObject emergency) {
        try {
            description = emergency.getString("description");
            website = emergency.getString("website");
            number = emergency.getString("number");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //set the emergency info to the appropriate UI variables
    private void setInfo() {
        txtDescription.setText(description);

        //Set the emergency website text in the UI to clickable
        //making it a hyperlink which will open the link in the browser
        txtWebsite.setClickable(true);
        txtWebsite.setMovementMethod(LinkMovementMethod.getInstance());
        String text = website;
        txtWebsite.setText(Html.fromHtml(text));
    }
}
