package com.example.msusportsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.StringRequest;
import com.example.msusportsapp.R;
import com.example.msusportsapp.utilities.ApiUrl;
import com.example.msusportsapp.utilities.AppSingleton;
import com.example.msusportsapp.utilities.SharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {

    private static final int TIME_OUT = 4000;
    private SharedPref sharedPref = new SharedPref(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        //if user once logged in login info
        //is queried from shared preferences
        // and checked for validity

        final String sharedUsername = sharedPref.getString("username", "");
        final String sharedPassword = sharedPref.getString("password", "");
      // final String sharedSport = sharedPref.getString("sport", "");

        new Handler().postDelayed(() -> {

            if (!(sharedPassword.equals("") && sharedUsername.equals(""))) {
                login();
            }
            else {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },TIME_OUT);
    }


    private void login() {

        StringRequest myStringRequest = new StringRequest(Request.Method.POST, ApiUrl.URL_LOGIN_CHECK, response -> {

            try {

                // gets response from php file for success or failure
                JSONArray jsonArray = new JSONArray(response);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String code = jsonObject.getString("code");
                String message = jsonObject.getString("message");
                switch (code) {
                    case "Login admin":
                        Intent intent = new Intent(this, AdminActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        break;
                    case "Login coach":

                        CoachActivity.startIntent(SplashScreen.this, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK, message);

                        break;
                    case "Login student":

                        StudentActivity.startIntent(SplashScreen.this, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        break;

                    case "Login failed":
                        Toast.makeText(SplashScreen.this, "Your password has been recently changed please input the new one"
                                , Toast.LENGTH_LONG).show();
                        LoginActivity.startIntent(this, Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        break;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }, error -> {

            if (error instanceof TimeoutError) {
                Toast.makeText(SplashScreen.this, "Login attempt has timed out. Please try again.",
                        Toast.LENGTH_LONG).show();

            } else if (error instanceof NetworkError) {
                Toast.makeText(SplashScreen.this, "Network Error", Toast.LENGTH_LONG).show();

            } else if (error instanceof ServerError) {
                Toast.makeText(SplashScreen.this, "Server is down", Toast.LENGTH_LONG).show();

            }
            error.printStackTrace();


        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                String sharedUsername = sharedPref.getString("username", "");
                String sharedPassword = sharedPref.getString("password", "");
                params.put("Regnum", sharedUsername);
                params.put("Password", sharedPassword);

                return params;

            }
        };

        AppSingleton.getInstance(SplashScreen.this).addToRequestQueue(myStringRequest);


    }
}
