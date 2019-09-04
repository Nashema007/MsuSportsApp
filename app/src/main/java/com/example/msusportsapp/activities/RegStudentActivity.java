package com.example.msusportsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
import com.example.msusportsapp.modals.Person;
import com.example.msusportsapp.utilities.ApiUrl;
import com.example.msusportsapp.utilities.AppSingleton;
import com.example.msusportsapp.utilities.SharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegStudentActivity extends AppCompatActivity {

    private List<String> degreeList = new ArrayList<>();
    private Spinner prog;
    private Spinner level;
    private EditText name;
    private EditText surname;
    private EditText regnum;

    private Person person = new Person();
    private SharedPref sharedPref = new SharedPref(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_student);

        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Sports Registration");
            actionBar.show();
        }

        prog = findViewById(R.id.studentProgram);
        level = findViewById(R.id.studentLevel);
        name = findViewById(R.id.studentName);
        surname = findViewById(R.id.studentSurname);
        regnum = findViewById(R.id.studentRegnum);
        Spinner sex = findViewById(R.id.studentSex);
        Spinner sport = findViewById(R.id.studentSports);
        Button submit = findViewById(R.id.submitRegInfo);


        addSpinnerItems();
        submit.setOnClickListener((v) -> {

            validate();
            String strRegnum = regnum.getText().toString().trim(); // Level 4.1
            String strLevel = level.getSelectedItem().toString().substring(6);
            String strProg = prog.getSelectedItem().toString();
            String strGender = sex.getSelectedItem().toString();
            String strName = name.getText().toString();
            String strSurname = surname.getText().toString();
            String strSport = sport.getSelectedItem().toString().toLowerCase();

           sharedPref.saveString("sport", strSport);


            person.setRegnum(strRegnum);
            person.setGender(strGender);
            person.setProg(strProg);
            person.setLevel(strLevel);
            person.setName(strName);
            person.setSurname(strSurname);
            person.setSport(strSport);

            setDetails();

        });

    }



    public void setDetails() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrl.URL_POST_REG_DETAILS, response -> {

            try {
                JSONArray jsonArray = new JSONArray(response);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String code = jsonObject.getString("code");
                String message = jsonObject.getString("message");
                if (code.equals("successful")) {

                    StudentActivity.startIntent(RegStudentActivity.this, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);


                } else if (code.equals("failed")) {

                    Toast.makeText(RegStudentActivity.this, message, Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            if (error instanceof TimeoutError) {
                Toast.makeText(RegStudentActivity.this, "Login attempt has timed out. Please try again.",
                        Toast.LENGTH_LONG).show();

            } else if (error instanceof NetworkError) {
                Toast.makeText(RegStudentActivity.this, "Network Error", Toast.LENGTH_LONG).show();

            } else if (error instanceof ServerError) {
                Toast.makeText(RegStudentActivity.this, "Server is down", Toast.LENGTH_LONG).show();

            }
            error.printStackTrace();
        }) {

            @Override
            protected Map<String, String> getParams() {

                // stores the login details using key pair system
                Map<String, String> params = new HashMap<>();
                params.put("regnum", person.getRegnum());
                params.put("level", person.getLevel());
                params.put("degree", person.getProg());
                params.put("gender", person.getGender());
                params.put("surname", person.getSurname());
                params.put("name", person.getName());
                params.put("sport", person.getSport());

                return params;

            }

        };
        AppSingleton.getInstance(this).addToRequestQueue(stringRequest);


    }

    public void addSpinnerItems() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiUrl.URL_GET_DEGREES, response -> {
            try {
                JSONArray array = new JSONArray(response);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    String degreeDetails = jsonObject.getString("program_name");
                    degreeList.add(degreeDetails);

                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(RegStudentActivity.this, android.R.layout.simple_spinner_item, degreeList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                prog.setAdapter(dataAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }, error -> {
            if (error instanceof TimeoutError) {
                Toast.makeText(RegStudentActivity.this, "Login attempt has timed out. Please try again.",
                        Toast.LENGTH_LONG).show();

            } else if (error instanceof NetworkError) {
                Toast.makeText(RegStudentActivity.this, "Network Error", Toast.LENGTH_LONG).show();

            } else if (error instanceof ServerError) {
                Toast.makeText(RegStudentActivity.this, "Server is down", Toast.LENGTH_LONG).show();

            }
            error.printStackTrace();


        });

        AppSingleton.getInstance(this).addToRequestQueue(stringRequest);

    }


    private boolean validate() {
        boolean alldone;

        String strName = name.getText().toString().trim();
        String strReg = regnum.getText().toString().trim();
        String strSurname = surname.getText().toString().trim();


        if (strName.isEmpty()) {
            name.setError("Please Enter you Name");
            return false;
        } else {
            alldone = true;
            name.setError(null);
        }
        if (strSurname.isEmpty()) {
            surname.setError("Please Enter your Surname");
            return false;
        } else {
            alldone = true;
            surname.setError(null);
        }
        if (strReg.isEmpty()) {
            regnum.setError("Enter your Reg Number");
            return false;
        } else {
            alldone = true;
            regnum.setError(null);
            return alldone;

        }


    }

}
