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

public class RestaurantActivity extends Activity {

    int arrayPosition;

    TextView txtRestaurant1;
    TextView txtRestaurant2;
    TextView txtInfoR1;
    TextView txtInfoR2;

    String restaurant1TXT;
    String restaurant2TXT;
    String infor1;
    String infor2;

    ImageView imgRestaurant1;
    ImageView imgRestaurant2;

    final int RESTAURANT_1 = 0;
    final int RESTAURANT_2 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        txtRestaurant1 = findViewById(R.id.txt_restaurant1);
        txtRestaurant2 = findViewById(R.id.txt_restaurant2);
        txtInfoR1 = findViewById(R.id.txt_info_r1);
        txtInfoR2 = findViewById(R.id.txt_info_r2);
        imgRestaurant1 = findViewById(R.id.img_restaurant1);
        imgRestaurant2 = findViewById(R.id.img_restaurant2);

        restaurant1TXT = "";
        restaurant2TXT = "";
        infor1 = "";
        infor2 = "";


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
            JSONArray restaurants = city.getJSONArray("restaurants");
            getInfo(restaurants);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void loadImages(int position) {
        switch (position) {
            case 0:
                imgRestaurant1.setImageDrawable(getResources().getDrawable(R.drawable.carisma));
                imgRestaurant2.setImageDrawable(getResources().getDrawable(R.drawable.alorestaurant));
                break;

            case 1:
                imgRestaurant1.setImageDrawable(getResources().getDrawable(R.drawable.theledbury));
                imgRestaurant2.setImageDrawable(getResources().getDrawable(R.drawable.tangia));
                break;

            case 2:
                imgRestaurant1.setImageDrawable(getResources().getDrawable(R.drawable.latequeria));
                imgRestaurant2.setImageDrawable(getResources().getDrawable(R.drawable.garydanko));
                break;

            case 3:
                imgRestaurant1.setImageDrawable(getResources().getDrawable(R.drawable.mamak));
                imgRestaurant2.setImageDrawable(getResources().getDrawable(R.drawable.mrwong));
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

    private void getInfo(JSONArray restaurants) {
        try {
            JSONObject restaurant1 = restaurants.getJSONObject(RESTAURANT_1);
            JSONObject restaurant2 = restaurants.getJSONObject(RESTAURANT_2);

            restaurant1TXT = restaurant1.getString("name");
            restaurant2TXT = restaurant2.getString("name");

            StringBuilder infoR1Builder = new StringBuilder();
            StringBuilder infoR2Builder = new StringBuilder();



            infoR1Builder.append("Food Type: ").append(restaurant1.getString("food-type"))
                    .append("\n")
                    .append("Address: ").append(restaurant1.getString("address"))
                    .append("\n").append("\n")
                    .append("Website: ").append(restaurant1.getString("website"))
                    .append("\n").append("\n")
                    .append("Review By: ").append(restaurant1.getString("review-by"))
                    .append("\n")
                    .append("\"").append(restaurant1.getString("review")).append("\"")
                    .append("\n").append("\n")
                    .append("Stars: ").append(restaurant1.getString("stars"))
                    .append("\n").append("\n");

            infoR2Builder.append("Food Type: ").append(restaurant2.getString("food-type"))
                    .append("\n")
                    .append("Address: ").append(restaurant2.getString("address"))
                    .append("\n").append("\n")
                    .append("Website: ").append(restaurant2.getString("website"))
                    .append("\n").append("\n")
                    .append("Review By: ").append(restaurant2.getString("review-by"))
                    .append("\n")
                    .append("\"").append(restaurant2.getString("review")).append("\"")
                    .append("\n").append("\n")
                    .append("Stars: ").append(restaurant2.getString("stars"))
                    .append("\n").append("\n");

            infor1 = infoR1Builder.toString();
            infor2 = infoR2Builder.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setInfo() {
        txtRestaurant1.setText(restaurant1TXT);
        txtRestaurant2.setText(restaurant2TXT);
        txtInfoR1.setText(infor1);
        txtInfoR2.setText(infor2);
    }
}
