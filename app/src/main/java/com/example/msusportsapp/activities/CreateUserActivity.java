package com.example.msusportsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.StringRequest;
import com.example.msusportsapp.R;
import com.example.msusportsapp.modals.CreateUser;
import com.example.msusportsapp.utilities.ApiUrl;
import com.example.msusportsapp.utilities.AppSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CreateUserActivity extends AppCompatActivity {

    private CreateUser userCreation = new CreateUser();
    private String strSport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Create User");
        }
        EditText username = findViewById(R.id.users_id);
        Spinner accessLevel = findViewById(R.id.userAccessLevel);
        Spinner sportType = findViewById(R.id.userType);
        Button createUser = findViewById(R.id.createUser);

        createUser.setOnClickListener((v)->{

            String strUser = username.getText().toString();
            String strLevel = accessLevel.getSelectedItem().toString();

            if((accessLevel.getSelectedItem().toString()).equals("Student")){
                strSport = "null";
            }
            else if((accessLevel.getSelectedItem().toString()).equals("Coach")){
                strSport = sportType.getSelectedItem().toString();
            }

            if (TextUtils.isEmpty(strUser)){
                username.setError("Enter Username");
            }
            else {

                userCreation.setSport(strSport);
                userCreation.setAccessLevel(strLevel);
                userCreation.setUser(strUser);
                sendUserData();

            }
        });


    }

    private void sendUserData() {
        StringRequest myStringRequest = new StringRequest(Request.Method.POST, ApiUrl.URL_POST_USER_CREATION, response -> {

            try {

                // gets response from php file for success or failure
                JSONArray jsonArray = new JSONArray(response);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String code = jsonObject.getString("code");
                String message = jsonObject.getString("message");

                switch (code) {
                    case "successful":
                        Intent intent = new Intent(this, AdminActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        break;
                    case "failed":

                        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

                        break;
                }

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

        }) {
            @Override
            protected Map<String, String> getParams() {

                // stores the login details using key pair system
                Map<String, String> params = new HashMap<>();
                String dateTime = Calendar.getInstance().getTime().toString();
                params.put("username", userCreation.getUser());
                params.put("accessLevel", userCreation.getAccessLevel());
                params.put("sport", userCreation.getSport());
                params.put("Login", dateTime);

                return params;

            }
        };

        AppSingleton.getInstance(this).addToRequestQueue(myStringRequest);


    }
}
