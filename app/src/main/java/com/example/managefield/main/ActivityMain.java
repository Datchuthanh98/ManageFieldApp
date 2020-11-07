package com.example.managefield.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.managefield.R;
import com.example.managefield.view.Adapter.AdapterFragmentField;
import com.google.android.material.tabs.TabLayout;

public class ActivityMain extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        super.onCreate(savedInstanceState);
        TabLayout tabLayout = findViewById(R.id.tablayout);
        ViewPager viewPager = findViewById(R.id.viewpager);
        FragmentManager manager = getSupportFragmentManager();
        AdapterFragmentField adapter = new AdapterFragmentField(manager, AdapterFragmentField.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.tab_match);
        tabLayout.getTabAt(1).setIcon(R.drawable.booking);
        tabLayout.getTabAt(2).setIcon(R.drawable.time_tab_icon);
        tabLayout.getTabAt(3).setIcon(R.drawable.field_24);

    }


    public void addFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, null).addToBackStack(null).commit();
    }




}