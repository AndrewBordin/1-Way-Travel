package com.example.andrew.project_bordin_costa.model;

import android.graphics.drawable.Drawable;

import com.example.andrew.project_bordin_costa.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Andrew on 12/28/2017.
 */

public class Destination implements Serializable {

    private String CityName;

    private int CityPicture;

    public Destination(String cityName, int cityPicture){
        this.CityPicture = cityPicture;
        this.CityName = cityName;
    }

    public String getCityName(){return CityName;}

    public int getCityPicture(){return CityPicture;}

    public static ArrayList<Destination> getDestination(){

        ArrayList<Destination> destinations = new ArrayList<>();

        destinations.add(new Destination("Toronto", R.drawable.toronto4));
        destinations.add(new Destination("London", R.drawable.london2));
        destinations.add(new Destination("San Fransisco", R.drawable.sanfran2));
        destinations.add(new Destination("Sydney", R.drawable.sydney));

        return destinations;
    }

}
