package com.example.msusportsapp.activities;

import android.os.Bundle;

import com.example.msusportsapp.R;
import com.example.msusportsapp.adapters.ViewPagerAdapter;
import com.example.msusportsapp.fragments.EventsFrag;
import com.example.msusportsapp.fragments.SportUpdateFrag;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class EventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);


        TabLayout tabLayout =findViewById(R.id.NewsEventsTab);
        ViewPager viewPager = findViewById(R.id.ViewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());


        viewPagerAdapter.addFragment(new SportUpdateFrag(), "Sport Update");
        viewPagerAdapter.addFragment(new EventsFrag(), "Event");


        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
