package com.example.msusportsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.StringRequest;
import com.example.msusportsapp.R;
import com.example.msusportsapp.modals.LevelValues;
import com.example.msusportsapp.utilities.ApiUrl;
import com.example.msusportsapp.utilities.AppSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class StatsPerLevelActivity extends AppCompatActivity {

    private TextView level_one_value;
    private TextView level_two_value;
    private TextView level_three_value;
    private TextView level_four_value;
    private TextView level_five_value;
    private TextView viewGraph;
    private LevelValues levelValues = new LevelValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_per_level);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Stats of Players per Level");
        }

        level_one_value = findViewById(R.id.level_one_value);
        level_two_value = findViewById(R.id.level_two_value);
        level_three_value = findViewById(R.id.level_three_value);
        level_four_value = findViewById(R.id.level_four_value);
        level_five_value = findViewById(R.id.level_five_value);
        viewGraph = findViewById(R.id.viewGraph);


        String soccer = getIntent().getStringExtra("soccer");
        String basketball = getIntent().getStringExtra("basketball");
        String hockey = getIntent().getStringExtra("hockey");

        String url = " ";
        String extra = setURL(soccer, basketball, hockey);
        switch (extra) {
            case "basketball_students":
                url = ApiUrl.URL_GET_BASKETBALL_STUDENTS;
                break;
            case "hockey_students":
                url = ApiUrl.URL_GET_HOCKEY_STUDENTS;
                break;
            case "soccer_students":
                url = ApiUrl.URL_GET_SOCCER_STUDENTS;
                break;
        }

        fetchStudents(url, extra);






    }


    private String setURL(String soccer, String basketball, String hockey) {

        String extra = " ";
        if (soccer == null && hockey == null) {
            soccer = " ";
            hockey = " ";
            extra = "basketball_students";


        } else if (soccer == null && basketball == null) {

            soccer = " ";
            basketball = " ";

            extra = "hockey_students";

        } else if (basketball == null && hockey == null) {

            basketball = " ";
            hockey = " ";
            extra = "soccer_students";

        }

        return extra;
    }

    private void fetchStudents(String url, String extra) {

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                JSONObject eventsArray = jsonArray.getJSONObject(0);
                JSONArray eventsDetails = eventsArray.getJSONArray(extra);
                ArrayList<Integer> yAxis = new ArrayList<>();
                int level_one_one = 0;
                int level_one_two = 0;
                int level_two_one = 0;
                int level_two_two = 0;
                int level_three_one = 0;
                int level_three_two = 0;
                int level_four_one = 0;
                int level_four_two = 0;
                int level_five_one = 0;
                int level_five_two = 0;

                ArrayList<String> level = new ArrayList<>();

                for (int i = 0; i < eventsDetails.length(); i++) {
                    JSONObject index = eventsDetails.getJSONObject(i);
                    String levelSport = index.getString("level");
                    level.add(levelSport);
                    }

                for (int j = 0; j < level.size(); j++) {
                    switch (level.get(j)) {
                        case "1.1":
                            level_one_one++;
                            break;
                        case "1.2":
                            level_one_two++;
                            break;
                        case "2.1":
                            level_two_one++;
                            break;
                        case "2.2":
                            level_two_two++;
                            break;
                        case "3.1":
                            level_three_one++;
                            break;
                        case "3.2":
                            level_three_two++;
                            break;
                        case "4.1":
                            level_four_one++;
                            break;
                        case "4.2":
                            level_four_two++;
                            break;
                        case "5.1":
                            level_five_one++;
                            break;
                        case "5.2":
                            level_five_two++;
                            break;
                    }
                }



                yAxis.add((level_one_one +level_one_two));
                yAxis.add((level_two_one + level_two_two));
                yAxis.add((level_three_one + level_three_two));
                yAxis.add((level_four_one + level_four_two));
                yAxis.add((level_five_one + level_five_two));


                levelValues.setLevel_one_value(yAxis.get(0));
                levelValues.setLevel_two_value(yAxis.get(1));
                levelValues.setLevel_three_value(yAxis.get(2));
                levelValues.setLevel_four_value(yAxis.get(3));
                levelValues.setLevel_five_value(yAxis.get(4));

                level_one_value.setText(String.valueOf(levelValues.getLevel_one_value()));
                level_two_value.setText(String.valueOf(levelValues.getLevel_two_value()));
                level_three_value.setText(String.valueOf(levelValues.getLevel_three_value()));
                level_four_value.setText(String.valueOf(levelValues.getLevel_four_value()));
                level_five_value.setText(String.valueOf(levelValues.getLevel_five_value()));

                viewGraph.setOnClickListener((v)-> {

                    Intent intent = new Intent(this,DrawGraphActivity.class);
                    intent.putExtra("statsLevel", "statsLevel");
                    intent.putIntegerArrayListExtra("yAxis", yAxis);
                    startActivity(intent);
                });




            } catch (JSONException e) {
                e.printStackTrace();
            }


        }, error -> {
            if (error instanceof TimeoutError) {
                Toast.makeText(this, "Login attempt has timed out. Please try again.",
                        Toast.LENGTH_LONG).show();

            } else if (error instanceof NetworkError) {
                Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show();

            } else if (error instanceof ServerError) {
                Toast.makeText(this, "Server is down", Toast.LENGTH_LONG).show();

            }
            error.printStackTrace();


        });

        AppSingleton.getInstance(this).addToRequestQueue(stringRequest);


    }
}
