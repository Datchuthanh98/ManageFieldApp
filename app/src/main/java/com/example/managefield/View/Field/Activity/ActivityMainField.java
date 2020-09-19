package com.example.managefield.View.Field.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.managefield.R;
import com.example.managefield.View.Field.Adapter.AdapterFragmentField;
import com.example.managefield.Animation.HorizontalFlipTransformation;
import com.google.android.material.tabs.TabLayout;

public class ActivityMainField extends AppCompatActivity {
//    private String emailLogin, phoneLogin;
//    private SQLiteHelper sqLiteHelper;
//    private boolean isManager, isOnline;
//    private SharedPreferences sharedPref;
//    private SyncFireBase syncFireBase = SyncFireBase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main_field);
        super.onCreate(savedInstanceState);

        TabLayout tabLayout = findViewById(R.id.tablayout);
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setPageTransformer(true, new HorizontalFlipTransformation());
        FragmentManager manager = getSupportFragmentManager();
        AdapterFragmentField adapter = new AdapterFragmentField(manager, AdapterFragmentField.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_account_circle_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_account_circle_black_24dp);

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.mymenu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.mLogout:
////                FirebaseAuth.getInstance().signOut();
//                break;
//            case R.id.mPhone:
//                Toast.makeText(getApplicationContext(),
//                        "Phone",Toast.LENGTH_LONG).show();
//                break;
//            case R.id.mEmail:
//                Toast.makeText(getApplicationContext(),
//                        "Email",Toast.LENGTH_LONG).show();
//                break;
//            case R.id.mExit:
//                System.exit(0);
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }



}