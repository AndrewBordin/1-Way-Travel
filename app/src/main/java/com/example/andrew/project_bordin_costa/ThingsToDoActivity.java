package com.example.andrew.project_bordin_costa;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
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

    //variable declarations

    //variable which receives the city chosen
    int arrayPosition;

    //UI variables for the first thing to do in the city
    TextView txtName1;
    TextView txtTtdDescription1;
    TextView txtTtdAddress1;
    TextView txtTtdWebsite1;
    ImageView img1;

    //variables which will receive the first thing to do information
    String name1;
    String description1;
    String address1;
    String website1;

    //UI variables for the second thing to do in the city
    TextView txtName2;
    TextView txtTtdDescription2;
    TextView txtTtdAddress2;
    TextView txtTtdWebsite2;
    ImageView img2;

    //variables which will receive the second thing to do information
    String name2;
    String description2;
    String address2;
    String website2;

    //UI variables for the first third to do in the city
    TextView txtName3;
    TextView txtTtdDescription3;
    TextView txtTtdAddress3;
    TextView txtTtdWebsite3;
    ImageView img3;

    //variables which will receive the third thing to do information
    String name3;
    String description3;
    String address3;
    String website3;

    //the arrayPosition value for each city
    final int TORONTO = 0;
    final int LONDON = 1;
    final int SANFRAN = 2;
    final int SYDNEY = 3;

    //the value of the position in the thing to do array found in the json file
    final int THING_TO_DO_1 = 0;
    final int THING_TO_DO_2 = 1;
    final int THING_TO_DO_3 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_to_do);

        //Variable assignment
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

        //get the intent
        Intent intent = getIntent();

        //from the intent get the city chosen, load its info and the appropriate images to go along with the info
        //then set the info to the UI variables
        if(intent != null){
            arrayPosition = intent.getIntExtra("arrayPosition", 0);
            loadInfo(arrayPosition);
            loadImages(arrayPosition);
            setInfo();
        }

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
            //get the proper things to do's array accordingly to the city chosen
            JSONArray thingsToDo = city.getJSONArray("things-to-do");
            //then get the things to do information
            getInfo(thingsToDo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //method which will get the proper images of thing to do 1, 2 and 3
    //from drawable accordingly to the city chosen
    private void loadImages(int position) {

        switch (position){
            case TORONTO:
                img1.setImageDrawable(getResources().getDrawable(R.drawable.toronto2));
                img2.setImageDrawable(getResources().getDrawable(R.drawable.ripleys));
                img3.setImageDrawable(getResources().getDrawable(R.drawable.eatoncentre));
                break;

            case LONDON:
                img1.setImageDrawable(getResources().getDrawable(R.drawable.londoneye));
                img2.setImageDrawable(getResources().getDrawable(R.drawable.bigben));
                img3.setImageDrawable(getResources().getDrawable(R.drawable.buckinghampalace));
                break;

            case SANFRAN:
                img1.setImageDrawable(getResources().getDrawable(R.drawable.goldengate));
                img2.setImageDrawable(getResources().getDrawable(R.drawable.pier39));
                img3.setImageDrawable(getResources().getDrawable(R.drawable.coittower));
                break;

            case SYDNEY:
                img1.setImageDrawable(getResources().getDrawable(R.drawable.operahouse));
                img2.setImageDrawable(getResources().getDrawable(R.drawable.harbourbridge));
                img3.setImageDrawable(getResources().getDrawable(R.drawable.tarongazoo));
                break;

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

    //Get thing to do 1, 2 and 3 objects and their information, assigning the info to the
    //appropriate variables
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

    //set the things to do info to the appropriate UI variables
    private void setInfo() {
        txtName1.setText(name1);
        txtTtdDescription1.setText(description1);
        txtTtdAddress1.setText(address1);

        txtName2.setText(name2);
        txtTtdDescription2.setText(description2);
        txtTtdAddress2.setText(address2);

        txtName3.setText(name3);
        txtTtdDescription3.setText(description3);
        txtTtdAddress3.setText(address3);

        //Set all the websites' text in the UI to clickable
        //making it a hyperlink which will open the link in the browser
        txtTtdWebsite1.setClickable(true);
        txtTtdWebsite1.setMovementMethod(LinkMovementMethod.getInstance());
        txtTtdWebsite1.setText(Html.fromHtml(website1));

        txtTtdWebsite2.setClickable(true);
        txtTtdWebsite2.setMovementMethod(LinkMovementMethod.getInstance());
        txtTtdWebsite2.setText(Html.fromHtml(website2));

        txtTtdWebsite3.setClickable(true);
        txtTtdWebsite3.setMovementMethod(LinkMovementMethod.getInstance());
        txtTtdWebsite3.setText(Html.fromHtml(website3));


    }
}
