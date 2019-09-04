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
import com.example.msusportsapp.utilities.ApiUrl;
import com.example.msusportsapp.utilities.AppSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewAppRatingActivity extends AppCompatActivity {

    private TextView rate_one_value;
    private TextView rate_two_value;
    private TextView rate_three_value;
    private TextView rate_four_value;
    private TextView rate_five_value;
    private TextView viewGraph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_per_level);

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Stats of User Satisfaction");
        }
        rate_one_value = findViewById(R.id.level_one_value);
        rate_two_value = findViewById(R.id.level_two_value);
        rate_three_value = findViewById(R.id.level_three_value);
        rate_four_value = findViewById(R.id.level_four_value);
        rate_five_value = findViewById(R.id.level_five_value);

        TextView rate_one_label = findViewById(R.id.level_one_label);
        TextView rate_two_label = findViewById(R.id.level_two_label);
        TextView rate_three_label = findViewById(R.id.level_three_label);
        TextView rate_four_label = findViewById(R.id.level_four_label);
        TextView rate_five_label = findViewById(R.id.level_five_label);


        rate_one_label.setText(R.string.terrible);
        rate_two_label.setText(R.string.bad);
        rate_three_label.setText(R.string.okay);
        rate_four_label.setText(R.string.good);
        rate_five_label.setText(R.string.great);

        viewGraph = findViewById(R.id.viewGraph);

        fetchRatings();

    }

    private void fetchRatings() {

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiUrl.URL_GET_RATE, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                    JSONObject ratingArray = jsonArray.getJSONObject(0);
                    String first = ratingArray.getString("first");
                    String second = ratingArray.getString("second");
                    String third = ratingArray.getString("third");
                    String fourth = ratingArray.getString("fourth");
                    String fifth = ratingArray.getString("fifth");

                ArrayList<Integer> yAxis = new ArrayList<>();
                yAxis.add(Integer.parseInt(first));
                yAxis.add(Integer.parseInt(second));
                yAxis.add(Integer.parseInt(third));
                yAxis.add(Integer.parseInt(fourth));
                yAxis.add(Integer.parseInt(fifth));


                rate_one_value.setText(first);
                rate_two_value.setText(second);
                rate_three_value.setText(third);
                rate_four_value.setText(fourth);
                rate_five_value.setText(fifth);

                viewGraph.setOnClickListener((v)-> {

                    Intent intent = new Intent(this,DrawGraphActivity.class);
                    intent.putExtra("rating", "rating");
                    intent.putIntegerArrayListExtra("yAxis", yAxis);
                    startActivity(intent);
                });




            } catch (JSONException e) {
                e.printStackTrace();
            }


        }, error -> {
            if (error instanceof TimeoutError) {
                Toast.makeText(this, "Attempt has timed out. Please try again.",
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
