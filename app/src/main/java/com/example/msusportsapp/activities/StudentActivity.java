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
import androidx.cardview.widget.CardView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class StudentActivity extends AppCompatActivity {




    public static void startIntent(Context context, int flags) {
        Intent intent = new Intent(context, StudentActivity.class);
        intent.setFlags(flags);
        context.startActivity(intent);
    }
    public static void startIntent(Context context, int flags, String extra) {
        Intent intent = new Intent(context, StudentActivity.class);
        intent.putExtra(extra, extra);
        intent.setFlags(flags);
        context.startActivity(intent);
    }


private SharedPref sharedPref = new SharedPref(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
            actionBar.show();
        }

        LottieAnimationView registerBtn = findViewById(R.id.registerBtn);
        LottieAnimationView eventBtn = findViewById(R.id.eventsBtn);
        LottieAnimationView rateBtn = findViewById(R.id.rateBtn);
        CardView cardViewRegisterBtn = findViewById(R.id.cardViewRegisterStudent);
        CardView cardViewEventBtn = findViewById(R.id.studentEvents_Updates);
        CardView cardViewRateBtn = findViewById(R.id.rateApp);

        setupAnimation(registerBtn, 0.3f );
        setupAnimation(eventBtn, 0.5f );



        cardViewRegisterBtn.setOnClickListener((v) -> {
            Intent intent = new Intent(this, RegStudentActivity.class);
            startActivity(intent);

        });

        cardViewEventBtn.setOnClickListener((v)->{
            Intent intent = new Intent(this, EventsActivity.class);
            startActivity(intent);
        });

        boolean rated = sharedPref.getBoolean("rated", false);
        if(rated){
            cardViewRateBtn.setClickable(false);
            cardViewRateBtn.setAlpha(0.5f);
            rateBtn.setSpeed(0);
            rateBtn.setRepeatCount(0);
            //rateBtn.pauseAnimation();

        }else {
            setupAnimation(rateBtn, 0.5f );
            cardViewRateBtn.setOnClickListener((v) -> {
                Intent intent = new Intent(this, RateActivity.class);
                startActivity(intent);
            });
        }
        
    }


    public static void setupAnimation(LottieAnimationView lottieAnimationView, float speed){

        lottieAnimationView.setSpeed(speed);
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
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
