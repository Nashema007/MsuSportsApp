package com.example.msusportsapp.activities;

import android.os.Bundle;

import com.example.msusportsapp.R;
import com.example.msusportsapp.adapters.ViewPagerAdapter;
import com.example.msusportsapp.fragments.FirstTeamFemales;
import com.example.msusportsapp.fragments.FirstTeamMales;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class ViewTeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_team);

        TabLayout tabLayout =findViewById(R.id.NewsEventsTab);
        ViewPager viewPager = findViewById(R.id.ViewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());


        viewPagerAdapter.addFragment(new FirstTeamMales(), "Male Players");
        viewPagerAdapter.addFragment(new FirstTeamFemales(), "Female Players");


        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


}
