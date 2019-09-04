package com.example.msusportsapp.activities;

import android.os.Bundle;

import com.example.msusportsapp.R;
import com.example.msusportsapp.adapters.ViewPagerAdapter;
import com.example.msusportsapp.fragments.FemalePlayers;
import com.example.msusportsapp.fragments.MalePlayers;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class ChooseTeamActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_team);

        TabLayout tabLayout =findViewById(R.id.NewsEventsTab);
        ViewPager viewPager = findViewById(R.id.ViewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());


        viewPagerAdapter.addFragment(new MalePlayers(), "Male Players");
        viewPagerAdapter.addFragment(new FemalePlayers(), "Female Players");


        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);



    }


}
