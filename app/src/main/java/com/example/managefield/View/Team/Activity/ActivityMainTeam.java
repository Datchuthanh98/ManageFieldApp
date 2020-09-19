package com.example.managefield.View.Team.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.managefield.R;
import com.example.managefield.View.Team.Adapter.AdapterFragmentTeam;
import com.example.managefield.Animation.HorizontalFlipTransformation;
import com.google.android.material.tabs.TabLayout;

public class ActivityMainTeam extends AppCompatActivity {
//    private String emailLogin, phoneLogin;
//    private SQLiteHelper sqLiteHelper;
//    private boolean isManager, isOnline;
//    private SharedPreferences sharedPref;
//    private SyncFireBase syncFireBase = SyncFireBase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main_team);
        super.onCreate(savedInstanceState);



        TabLayout tabLayout = findViewById(R.id.tablayout);
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setPageTransformer(true, new HorizontalFlipTransformation());
        FragmentManager manager = getSupportFragmentManager();
        AdapterFragmentTeam adapter = new AdapterFragmentTeam(manager, AdapterFragmentTeam.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_account_circle_black_24dp);

    }





}