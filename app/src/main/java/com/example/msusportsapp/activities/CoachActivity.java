package com.example.msusportsapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.StringRequest;
import com.example.msusportsapp.R;
import com.example.msusportsapp.adapters.CoachAdapter;
import com.example.msusportsapp.modals.GetTitleModal;
import com.example.msusportsapp.utilities.ApiUrl;
import com.example.msusportsapp.utilities.AppSingleton;
import com.example.msusportsapp.utilities.SharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CoachActivity extends AppCompatActivity {


    ArrayList<GetTitleModal> coachModels = new ArrayList<>();
    String[] titles = {"Team stats per level",
            "Choose team members", "View team", "Team Updates"};
    SharedPref sharedPref = new SharedPref(this);

    public static void startIntent(Context context, int flags, String extra) {
        Intent intent = new Intent(context, CoachActivity.class);
        intent.setFlags(flags);
        intent.putExtra(extra, extra);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach);
        ActionBar actionBar = this.getSupportActionBar();


        RecyclerView coachRecyclerView = findViewById(R.id.coachRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        coachRecyclerView.setLayoutManager(layoutManager);
        coachRecyclerView.setHasFixedSize(true);

        int count = 0;
        for (String title : titles) {

            coachModels.add(new GetTitleModal(title));
            count++;
        }


        String soccer = getIntent().getStringExtra("soccer");
        String basketball = getIntent().getStringExtra("basketball");
        String hockey = getIntent().getStringExtra("hockey");


        String extra = setExtra(soccer, basketball, hockey);


        switch (extra) {
            case "basketball":
                if (actionBar != null) {
                    actionBar.setTitle(getString(R.string.basketball_team));
                }
                break;
            case "soccer":
                if (actionBar != null) {
                    actionBar.setTitle(getString(R.string.soccer_team));
                }
                break;
            case "hockey":
                if (actionBar != null) {
                    actionBar.setTitle(getString(R.string.hockey_team));
                }
                break;
        }


        CoachAdapter coachAdapter = new CoachAdapter(this, coachModels, extra);
        coachRecyclerView.setAdapter(coachAdapter);

    }

    private String setExtra(String soccer, String basketball, String hockey) {

        String extra = " ";

        if (soccer == null && hockey == null) {
            soccer = " ";
            hockey = " ";
            extra = basketball;


        } else if (soccer == null && basketball == null) {
            soccer = " ";
            basketball = " ";
            extra = hockey;

        } else if (basketball == null && hockey == null) {

            extra = soccer;
            basketball = " ";
            hockey = " ";

        }

        return extra;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {

           setDetails();

        }

        return super.onOptionsItemSelected(item);
    }

    public void setDetails() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrl.URL_POST_LOGOUT_TIME, response -> {

            try {
                JSONArray jsonArray = new JSONArray(response);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String code = jsonObject.getString("code");
                String message = jsonObject.getString("message");
                if (code.equals("successful")) {

                    sharedPref.clear();
                    LoginActivity.startIntent(this, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);


                } else if (code.equals("failed")) {

                    Toast.makeText(this, message, Toast.LENGTH_LONG).show();

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
                String dateTime = Calendar.getInstance().getTime().toString();
                Map<String, String> params = new HashMap<>();
                params.put("regnum",sharedPref.getString("username", ""));
                params.put("Logout", dateTime);

                return params;

            }

        };
        AppSingleton.getInstance(this).addToRequestQueue(stringRequest);


    }
}
