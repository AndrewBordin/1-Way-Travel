package com.example.andrew.project_bordin_costa.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Andrew on 12/28/2017.
 */

public class Destination implements Serializable {

    private String CityName;

    public Destination(String cityName){
        this.CityName = cityName;
    }

    public String getCityName(){return CityName;}

    public static ArrayList<Destination> getDestination(){

        ArrayList<Destination> destinations = new ArrayList<>();

        destinations.add(new Destination("Toronto"));
        destinations.add(new Destination("London"));
        destinations.add(new Destination("San Fransisco"));
        destinations.add(new Destination("Sydney"));

        return destinations;
    }

}
