package com.example.andrew.project_bordin_costa;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

import static android.content.ContentValues.TAG;

public class RestaurantActivity extends Activity {

    //variable declarations

    //variable which receives the city chosen
    int arrayPosition;

    //UI variables for the first restaurant
    TextView txtRestaurant1;
    TextView txtFoodType1;
    TextView txtAddress1;
    TextView txtStars1;
    TextView txtReview1;
    TextView txtReviewBy1;
    ImageView imgRestaurant1;
    Button btnWebsite1;

    //variables for restaurant 1 which receive the information from json
    String restaurant1TXT;
    String foodType1;
    String address1;
    String stars1;
    String review1;
    String reviewBy1;
    String website1;

    //value of restaurant 1 in the restaurant array found in the json file
    final int RESTAURANT_1 = 0;

    //UI variables for the second restaurant
    TextView txtRestaurant2;
    TextView txtFoodType2;
    TextView txtAddress2;
    TextView txtStars2;
    TextView txtReview2;
    TextView txtReviewBy2;
    ImageView imgRestaurant2;
    Button btnWebsite2;

    //variables for restaurant 2 which receive the information from json
    String restaurant2TXT;
    String foodType2;
    String address2;
    String stars2;
    String review2;
    String reviewBy2;
    String website2;

    //value of the restaurant 2 in the restaurant array found in the json file
    final int RESTAURANT_2 = 1;

    //the arrayPosition value for each city
    final int TORONTO = 0;
    final int LONDON = 1;
    final int SANFRAN = 2;
    final int SYDNEY = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        //Variable assignment
        txtRestaurant1 = findViewById(R.id.txt_restaurant1);
        txtFoodType1 = findViewById(R.id.txt_foodType1);
        txtAddress1 = findViewById(R.id.txt_address1);
        txtStars1 = findViewById(R.id.txt_stars1);
        txtReview1 = findViewById(R.id.txt_review1);
        txtReviewBy1 = findViewById(R.id.txt_reviewBy1);
        imgRestaurant1 = findViewById(R.id.img_restaurant1);
        btnWebsite1 = findViewById(R.id.btnWebsite1);

        txtRestaurant2 = findViewById(R.id.txt_restaurant2);
        txtFoodType2 = findViewById(R.id.txt_foodType2);
        txtAddress2 = findViewById(R.id.txt_address2);
        txtStars2 = findViewById(R.id.txt_stars2);
        txtReview2 = findViewById(R.id.txt_review2);
        txtReviewBy2 = findViewById(R.id.txt_reviewBy2);
        imgRestaurant2 = findViewById(R.id.img_restaurant2);
        btnWebsite2 = findViewById(R.id.btnWebsite2);

        restaurant1TXT = "";
        foodType1 = "";
        address1 = "";
        stars1 = "";
        review1 = "";
        reviewBy1 = "";
        website1 = "";


        restaurant2TXT = "";
        foodType2 = "";
        address2 = "";
        stars2 = "";
        review2 = "";
        reviewBy2 = "";
        website2 = "";


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

        //on click listeners to open connection with the restaurants' websites
        btnWebsite1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openURL(website1);
            }
        });

        btnWebsite2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openURL(website2);
            }
        });
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
            //get the proper restaurants' array accordingly to the city chosen
            JSONArray restaurants = city.getJSONArray("restaurants");
            //then get the restaurants' information
            getInfo(restaurants);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //method which will get the proper images of restaurants 1 and 2
    //from drawable accordingly to the city chosen
    private void loadImages(int position) {
        switch (position) {
            case TORONTO:
                imgRestaurant1.setImageDrawable(getResources().getDrawable(R.drawable.carisma));
                imgRestaurant2.setImageDrawable(getResources().getDrawable(R.drawable.alorestaurant));
                break;

            case LONDON:
                imgRestaurant1.setImageDrawable(getResources().getDrawable(R.drawable.theledbury));
                imgRestaurant2.setImageDrawable(getResources().getDrawable(R.drawable.tangia));
                break;

            case SANFRAN:
                imgRestaurant1.setImageDrawable(getResources().getDrawable(R.drawable.latequeria));
                imgRestaurant2.setImageDrawable(getResources().getDrawable(R.drawable.garydanko));
                break;

            case SYDNEY:
                imgRestaurant1.setImageDrawable(getResources().getDrawable(R.drawable.mamak));
                imgRestaurant2.setImageDrawable(getResources().getDrawable(R.drawable.mrwong));
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

    //Get both restaurant objects and their information, assigning the info to the
    //appropriate variables
    private void getInfo(JSONArray restaurants) {
        try {
            JSONObject restaurant1 = restaurants.getJSONObject(RESTAURANT_1);
            JSONObject restaurant2 = restaurants.getJSONObject(RESTAURANT_2);

            restaurant1TXT = restaurant1.getString("name");
            foodType1 = String.format(getResources().getString(R.string.food_type),
                            restaurant1.getString("food-type"));
            address1 = restaurant1.getString("address");
            stars1 = String.format(getResources().getString(R.string.stars),
                    restaurant1.getString("stars"));
            review1 = restaurant1.getString("review");
            reviewBy1 = restaurant1.getString("review-by");
            website1 = restaurant1.getString("website");

            restaurant2TXT = restaurant2.getString("name");
            foodType2 = String.format(getResources().getString(R.string.food_type),
                    restaurant2.getString("food-type"));
            address2 = restaurant2.getString("address");
            stars2 = String.format(getResources().getString(R.string.stars),
                    restaurant2.getString("stars"));
            review2 = restaurant2.getString("review");
            reviewBy2 = restaurant2.getString("review-by");
            website2 = restaurant2.getString("website");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //set the restaurants' info to the appropriate UI variables
    private void setInfo() {
        txtRestaurant1.setText(restaurant1TXT);
        txtFoodType1.setText(foodType1);
        txtAddress1.setText(address1);
        txtStars1.setText(stars1);
        txtReview1.setText(review1);
        txtReviewBy1.setText(reviewBy1);

        txtRestaurant2.setText(restaurant2TXT);
        txtFoodType2.setText(foodType2);
        txtAddress2.setText(address2);
        txtStars2.setText(stars2);
        txtReview2.setText(review2);
        txtReviewBy2.setText(reviewBy2);
    }

    //open a network connection with the restaurants's website URL gotten from the json object
    private void openURL(String website) {

        //Create a new intent
        Intent intent = new Intent();

        // set the action to VIEW
        intent.setAction(Intent.ACTION_VIEW);

        // set the data of the Intent to be the Uri represented by the String
        intent.setData(Uri.parse(website));

        // start activity that will handle Uri
        startActivity(intent);
    }
}
