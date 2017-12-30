package com.example.andrew.project_bordin_costa;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

import static android.content.ContentValues.TAG;

public class ThingsToDoActivity extends Activity {

    int arrayPosition;

    TextView txtName1;
    TextView txtTtdDescription1;
    TextView txtTtdAddress1;
    TextView txtTtdWebsite1;
    ImageView img1;

    String name1;
    String description1;
    String address1;
    String website1;


    TextView txtName2;
    TextView txtTtdDescription2;
    TextView txtTtdAddress2;
    TextView txtTtdWebsite2;
    ImageView img2;

    String name2;
    String description2;
    String address2;
    String website2;


    TextView txtName3;
    TextView txtTtdDescription3;
    TextView txtTtdAddress3;
    TextView txtTtdWebsite3;
    ImageView img3;

    String name3;
    String description3;
    String address3;
    String website3;

    final int THING_TO_DO_1 = 0;
    final int THING_TO_DO_2 = 1;
    final int THING_TO_DO_3 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_to_do);

        txtName1 = findViewById(R.id.txt_name1);
        txtTtdDescription1 = findViewById(R.id.txt_ttd_description1);
        txtTtdAddress1 = findViewById(R.id.txt_ttd_address1);
        txtTtdWebsite1 = findViewById(R.id.txt_ttd_website1);
        img1 = findViewById(R.id.img_1);

        name1 = "";
        description1 = "";
        address1 = "";
        website1 = "";

        txtName2 = findViewById(R.id.txt_name2);
        txtTtdDescription2 = findViewById(R.id.txt_ttd_description2);
        txtTtdAddress2 = findViewById(R.id.txt_ttd_address2);
        txtTtdWebsite2 = findViewById(R.id.txt_ttd_website2);
        img2 = findViewById(R.id.img_2);

        name2 = "";
        description2 = "";
        address2 = "";
        website2 = "";

        txtName3 = findViewById(R.id.txt_name3);
        txtTtdDescription3 = findViewById(R.id.txt_ttd_description3);
        txtTtdAddress3 = findViewById(R.id.txt_ttd_address3);
        txtTtdWebsite3 = findViewById(R.id.txt_ttd_website3);
        img3 = findViewById(R.id.img_3);

        name3 = "";
        description3 = "";
        address3 = "";
        website3 = "";

        Intent intent = getIntent();

        if(intent != null){
            arrayPosition = intent.getIntExtra("arrayPosition", 0);
            loadInfo(arrayPosition);
            loadImages(arrayPosition);
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
            JSONArray thingsToDo = city.getJSONArray("things-to-do");
            getInfo(thingsToDo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void loadImages(int position) {

        switch (position){
            case 0:
                img1.setImageDrawable(getResources().getDrawable(R.drawable.toronto2));
                img2.setImageDrawable(getResources().getDrawable(R.drawable.ripleys));
                img3.setImageDrawable(getResources().getDrawable(R.drawable.eatoncentre));
                break;

            case 1:
                img1.setImageDrawable(getResources().getDrawable(R.drawable.londoneye));
                img2.setImageDrawable(getResources().getDrawable(R.drawable.bigben));
                img3.setImageDrawable(getResources().getDrawable(R.drawable.buckinghampalace));
                break;

            case 2:
                img1.setImageDrawable(getResources().getDrawable(R.drawable.goldengate));
                img2.setImageDrawable(getResources().getDrawable(R.drawable.pier39));
                img3.setImageDrawable(getResources().getDrawable(R.drawable.coittower));
                break;

            case 3:
                img1.setImageDrawable(getResources().getDrawable(R.drawable.operahouse));
                img2.setImageDrawable(getResources().getDrawable(R.drawable.harbourbridge));
                img3.setImageDrawable(getResources().getDrawable(R.drawable.tarongazoo));
                break;

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

    private void getInfo(JSONArray thingsToDo) {
        try {

            JSONObject thingToDo1 = thingsToDo.getJSONObject(THING_TO_DO_1);
            JSONObject thingToDo2 = thingsToDo.getJSONObject(THING_TO_DO_2);
            JSONObject thingToDo3 = thingsToDo.getJSONObject(THING_TO_DO_3);

            name1 = thingToDo1.getString("name");
            description1 = thingToDo1.getString("description");
            address1 = thingToDo1.getString("address");
            website1 = thingToDo1.getString("website");

            name2 = thingToDo2.getString("name");
            description2 = thingToDo2.getString("description");
            address2 = thingToDo2.getString("address");
            website2 = thingToDo2.getString("website");

            name3 = thingToDo3.getString("name");
            description3 = thingToDo3.getString("description");
            address3 = thingToDo3.getString("address");
            website3 = thingToDo3.getString("website");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setInfo() {
        txtName1.setText(name1);
        txtTtdDescription1.setText(description1);
        txtTtdAddress1.setText(address1);
        txtTtdWebsite1.setText(website1);

        txtName2.setText(name2);
        txtTtdDescription2.setText(description2);
        txtTtdAddress2.setText(address2);
        txtTtdWebsite2.setText(website2);

        txtName3.setText(name3);
        txtTtdDescription3.setText(description3);
        txtTtdAddress3.setText(address3);
        txtTtdWebsite3.setText(website3);
    }
}
