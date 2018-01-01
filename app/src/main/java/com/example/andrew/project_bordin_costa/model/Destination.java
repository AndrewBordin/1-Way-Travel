package com.example.andrew.project_bordin_costa.model;

import com.example.andrew.project_bordin_costa.R;
import java.io.Serializable;
import java.util.ArrayList;

public class Destination implements Serializable {

    //Variable Declarations
    private String CityName;

    private String CitySubheading;

    private int CityPicture;

    //Destination constructor that takes the cities name, picture, and subheading
    public Destination(String cityName, int cityPicture, String citySubheading){
        this.CityPicture = cityPicture;
        this.CityName = cityName;
        this.CitySubheading = citySubheading;
    }

    //Getter Methods
    public String getCityName(){return CityName;}

    public int getCityPicture(){return CityPicture;}

    public String getCitySubheading(){return CitySubheading;}

    //Creating the arraylist of destinations and their info
    public static ArrayList<Destination> getDestination(){

        ArrayList<Destination> destinations = new ArrayList<>();

        //Adding each destination object to the destination arraylist
        destinations.add(new Destination("Toronto", R.drawable.toronto4, "The Six."));
        destinations.add(new Destination("London", R.drawable.london2, "Live. Love. London."));
        destinations.add(new Destination("San Francisco", R.drawable.sanfran, "The Hardest Part is Leaving."));
        destinations.add(new Destination("Sydney", R.drawable.sydney2, "Love Every Second."));

        //return the arraylist.
        return destinations;
    }

}
