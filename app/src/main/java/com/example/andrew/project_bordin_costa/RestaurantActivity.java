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

public class RestaurantActivity extends Activity {

    int arrayPosition;

    TextView txtInfo1;
    TextView txtInfo2;
    TextView txtReview1;
    TextView txtReview2;

    String info1;
    String info2;
    String review1;
    String review2;

    final int RESTAURANT_1 = 0;
    final int RESTAURANT_2 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        txtInfo1 = findViewById(R.id.txt_info1);
        txtInfo2 = findViewById(R.id.txt_info2);
        txtReview1 = findViewById(R.id.txt_review1);
        txtReview2 = findViewById(R.id.txt_review2);

        info1 = "";
        info2 = "";
        review1 = "";
        review2 = "";

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
            JSONArray restaurants = city.getJSONArray("restaurants");
            getInfo(restaurants);
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

    private void getInfo(JSONArray restaurants) {
        try {
            JSONObject restaurant1 = restaurants.getJSONObject(RESTAURANT_1);
            JSONObject restaurant2 = restaurants.getJSONObject(RESTAURANT_2);

            StringBuilder infoBuilder1 = new StringBuilder();
            StringBuilder infoBuilder2 = new StringBuilder();
            StringBuilder reviewBuilder1 = new StringBuilder();
            StringBuilder reviewBuilder2 = new StringBuilder();

            infoBuilder1.append("Restaurant Name: ").append(restaurant1.getString("name"))
                    .append("\n")
                    .append("Food Type: ").append(restaurant1.getString("food-type"))
                    .append("\n")
                    .append("Address: ").append(restaurant1.getString("address"))
                    .append("\n")
                    .append("Website: ").append(restaurant1.getString("website"))
                    .append("\n");

            reviewBuilder1.append("Review By: ").append(restaurant1.getString("review-by"))
                    .append("\n")
                    .append("Review: ").append(restaurant1.getString("review"))
                    .append("\n")
                    .append("Stars: ").append(restaurant1.getString("stars"))
                    .append("\n");

            infoBuilder2.append("Restaurant Name: ").append(restaurant2.getString("name"))
                    .append("\n")
                    .append("Food Type: ").append(restaurant2.getString("food-type"))
                    .append("\n")
                    .append("Address: ").append(restaurant2.getString("address"))
                    .append("Website: ").append(restaurant2.getString("website"))
                    .append("\n");

            reviewBuilder2.append("Review By: ").append(restaurant2.getString("review-by"))
                    .append("\n")
                    .append("Review: ").append(restaurant2.getString("review"))
                    .append("\n")
                    .append("Stars: ").append(restaurant2.getString("stars"))
                    .append("\n");

            info1 = infoBuilder1.toString();
            info2 = infoBuilder2.toString();
            review1 = reviewBuilder1.toString();
            review2 = reviewBuilder2.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setInfo() {
        txtInfo1.setText(info1);
        txtReview1.setText(review1);
        txtInfo2.setText(info2);
        txtReview2.setText(review2);
    }
}
