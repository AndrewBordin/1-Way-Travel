package com.example.andrew.project_bordin_costa;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.Scanner;

import static android.content.ContentValues.TAG;

public class ThingsToDoActivity extends Activity {

    int arrayPosition;

    TextView txtName1;
    TextView txtName2;
    TextView txtInfo1;
    TextView txtInfo2;

    String name1;
    String name2;
    String info1;
    String info2;

    final int THING_TO_DO_1 = 0;
    final int THING_TO_DO_2 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_to_do);

        txtName1 = findViewById(R.id.txt_name1);
        txtName2 = findViewById(R.id.txt_name2);
        txtInfo1 = findViewById(R.id.txt_info1);
        txtInfo2 = findViewById(R.id.txt_info2);

        name1 = "";
        name2 = "";
        info1 = "";
        info2 = "";

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
            JSONArray thingsToDo = city.getJSONArray("things-to-do");
            getInfo(thingsToDo);
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

    private void getInfo(JSONArray thingsToDo) {
        try {
            JSONObject thingToDo1 = thingsToDo.getJSONObject(THING_TO_DO_1);
            JSONObject thingToDo2 = thingsToDo.getJSONObject(THING_TO_DO_2);
            StringBuilder infoBuilder1 = new StringBuilder();
            StringBuilder infoBuilder2 = new StringBuilder();

            name1 = thingToDo1.getString("name");
            name2 = thingToDo2.getString("name");

            infoBuilder1.append("Description: ").append(thingToDo1.getString("description"))
                    .append("\n").append("\n")
                    .append("Address: ").append(thingToDo1.getString("address"))
                    .append("\n").append("\n")
                    .append("Website: ").append(thingToDo1.getString("website"));

            infoBuilder2.append("Description: ").append(thingToDo2.getString("description"))
                    .append("\n").append("\n")
                    .append("Address: ").append(thingToDo2.getString("address"))
                    .append("\n").append("\n")
                    .append("Website: ").append(thingToDo2.getString("website"));

            info1 = infoBuilder1.toString();
            info2 = infoBuilder2.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setInfo() {
        txtName1.setText(name1);
        txtName2.setText(name2);
        txtInfo1.setText(info1);
        txtInfo2.setText(info2);
    }
}
