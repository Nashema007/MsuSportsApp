package com.example.msusportsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.StringRequest;
import com.example.msusportsapp.R;
import com.example.msusportsapp.utilities.ApiUrl;
import com.example.msusportsapp.utilities.AppSingleton;
import com.example.msusportsapp.utilities.SharedPref;
import com.hsalf.smilerating.SmileRating;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RateActivity extends AppCompatActivity {


private SharedPref sharedPref = new SharedPref(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        SmileRating smileRating = findViewById(R.id.smile_rating);
        smileRating.setOnRatingSelectedListener((level, reselected) -> {

            int ratingLevel = smileRating.getRating();
            if (ratingLevel != 0){
                sendRating(level);
            }

        });



    }

    private void sendRating(int level) {
        /*
        * Include logic to send rating with username to db and create graph activity for admin
         */
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrl.URL_POST_RATE, response -> {

            try {
                JSONArray jsonArray = new JSONArray(response);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String code = jsonObject.getString("code");
                if (code.equals("successful")) {
                    Intent intent = new Intent(RateActivity.this, StudentActivity.class);
                    sharedPref.saveBoolean("rated", true);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(RateActivity.this, "You chose a rating of "+level+". Thanks for the rating", Toast.LENGTH_LONG).show();


                } else if (code.equals("failed")) {

                    Toast.makeText(this, "Please try again", Toast.LENGTH_LONG).show();

                }
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
        }) {

            @Override
            protected Map<String, String> getParams() {

                // stores the login details using key pair system
                Map<String, String> params = new HashMap<>();
                params.put("username",sharedPref.getString("username", ""));
                params.put("rate", String.valueOf(level));

                return params;

            }

        };
        AppSingleton.getInstance(this).addToRequestQueue(stringRequest);




    }


}
