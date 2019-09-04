package com.example.msusportsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msusportsapp.R;
import com.example.msusportsapp.adapters.AdminAdapter;
import com.example.msusportsapp.modals.GetTitleModal;
import com.example.msusportsapp.utilities.SharedPref;

import java.util.ArrayList;


public class AdminActivity extends AppCompatActivity {


    private SharedPref sharedPref = new SharedPref(this);

    private String titles[] = {"View Login Times", "Delete user","Create User", "View App Rating", "Event Updates"};
    private ArrayList<GetTitleModal> getTitleModals =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ActionBar actionBar =this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Admin Portal");
            actionBar.show();
        }

        RecyclerView adminRecyclerView = findViewById(R.id.adminRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adminRecyclerView.setLayoutManager(layoutManager);
        adminRecyclerView.setHasFixedSize(true);
        int count= 0;
        for(String title:titles){
            getTitleModals.add(new GetTitleModal(title));

            count++;
        }
        AdminAdapter adminAdapter = new AdminAdapter(this, getTitleModals);
        adminRecyclerView.setAdapter(adminAdapter);


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

            sharedPref.clear();
            LoginActivity.startIntent(this, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        }


        return super.onOptionsItemSelected(item);
    }
}
