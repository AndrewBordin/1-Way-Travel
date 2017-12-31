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

    private String CitySubheading;

    private int CityPicture;

    public Destination(String cityName, int cityPicture, String citySubheading){
        this.CityPicture = cityPicture;
        this.CityName = cityName;
        this.CitySubheading = citySubheading;
    }

    public String getCityName(){return CityName;}

    public int getCityPicture(){return CityPicture;}

    public String getCitySubheading(){return CitySubheading;}

    public static ArrayList<Destination> getDestination(){

        ArrayList<Destination> destinations = new ArrayList<>();

        destinations.add(new Destination("Toronto", R.drawable.toronto4, "The Six."));
        destinations.add(new Destination("London", R.drawable.london2, "Live. Love. London."));
        destinations.add(new Destination("San Francisco", R.drawable.sanfran, "The Hardest Part is Leaving."));
        destinations.add(new Destination("Sydney", R.drawable.sydney2, "Love Every Second."));

        return destinations;
    }

}
