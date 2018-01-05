package com.example.andrew.project_bordin_costa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


//splash activity learned from https://medium.com/@ssaurel/create-a-splash-screen-on-android-the-right-way-93d6fb444857
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Launch the main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
