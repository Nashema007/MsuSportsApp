package com.example.msusportsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.StringRequest;
import com.example.msusportsapp.R;
import com.example.msusportsapp.modals.UpdatesModel;
import com.example.msusportsapp.utilities.ApiUrl;
import com.example.msusportsapp.utilities.AppSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class EventUpdatesActivity extends AppCompatActivity {
    private UpdatesModel updatesModel = new UpdatesModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_updates);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {

            actionBar.setTitle("Event Updates");
        }

        TextView updateTitle = findViewById(R.id.updateTitle);
        TextView updateBody = findViewById(R.id.updateBody);
        Button submitUpdate = findViewById(R.id.submitUpdate);

        submitUpdate.setOnClickListener( (v) -> {

            updatesModel.setTitle(updateTitle.getText().toString());
            updatesModel.setBody(updateBody.getText().toString());


            submitEventValues();
        });
    }

    private void submitEventValues() {
        StringRequest myStringRequest = new StringRequest(Request.Method.POST, ApiUrl.URL_POST_UPDATES, response -> {

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
                Toast.makeText(this, "Submit attempt has timed out. Please try again.",
                        Toast.LENGTH_LONG).show();

            } else if (error instanceof NetworkError) {
                Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show();

            } else if (error instanceof ServerError) {
                Toast.makeText(this, "Server is down", Toast.LENGTH_LONG).show();


            }
            error.printStackTrace();

        }) {
            @Override
            protected Map<String, String> getParams(){

                // stores the login details using key pair system
                Map<String, String> params = new HashMap<>();
                params.put("title", updatesModel.getTitle());
                params.put("body", updatesModel.getBody());
                params.put("sport", "admin");
                return params;

            }
        };

        AppSingleton.getInstance(this).addToRequestQueue(myStringRequest);


    }
}
