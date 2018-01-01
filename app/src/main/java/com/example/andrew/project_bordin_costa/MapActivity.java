package com.example.andrew.project_bordin_costa;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.andrew.project_bordin_costa.model.Destination;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    //Declaring Variables
    private GoogleMap mMap;
    int arrayPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //get the intent from the destination activity
        Intent intent = getIntent();

        if(intent != null){
            arrayPosition = intent.getIntExtra("arrayPosition", 0);

            //set the marker to the destination that was sent in the intent.
            SetMarker(arrayPosition);
        }

    }

    private void SetMarker(int arrayPosition) {
        //Set the height of the camera zoom. Can go up to 21
        float zoomLevel = 10.0f;

        switch (arrayPosition){
            case 0:
                // Add a marker in Toronto and move the camera
                LatLng toronto = new LatLng(43.657477, -79.383648);
                mMap.addMarker(new MarkerOptions().position(toronto).title("Marker in Toronto"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(toronto, zoomLevel));
                break;

            case 1:
                // Add a marker in London and move the camera
                LatLng london = new LatLng(51.507400, -0.127955);
                mMap.addMarker(new MarkerOptions().position(london).title("Marker in London"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(london, zoomLevel));
                break;

            case 2:
                // Add a marker in San Fransisco and move the camera
                LatLng sanfran = new LatLng(37.775049,  -122.419521);
                mMap.addMarker(new MarkerOptions().position(sanfran).title("Marker in San Fransisco"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sanfran, zoomLevel));
                break;

            case 3:
                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(-33.868563, 151.208732);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));
                break;
        }

    }
}
