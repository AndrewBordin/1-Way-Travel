package com.example.andrew.project_bordin_costa;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

    Button btnToronto;
    Button btnLondon;
    Button btnSanFran;
    Button btnSydney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToronto = findViewById(R.id.btnToronto);
        btnToronto.setOnClickListener(this);

        btnLondon = findViewById(R.id.btnLondon);
        btnLondon.setOnClickListener(this);

        btnSanFran = findViewById(R.id.btnSanFran);
        btnSanFran.setOnClickListener(this);

        btnSydney = findViewById(R.id.btnSydney);
        btnSydney.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.btnToronto:
                //code
                break;

            case R.id.btnLondon:
                //code
                break;

            case R.id.btnSanFran:
                //code
                break;

            case R.id.btnSydney:
                //code
                break;
        }

    }
}
