package com.example.managefield.View.Field.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.managefield.View.Field.Fragment.FragmentListMatch;
import com.example.managefield.View.Field.Fragment.FragmentProfileField;

public class AdapterFragmentField extends FragmentPagerAdapter {
    int numTab = 2;
    public AdapterFragmentField(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentProfileField();
            case 1:
                return new FragmentListMatch();
//            case 1:
//                if(isOnline){
//                if(isManager){
//                    return new FragmentManagePlayer();
//                }else{
//                    return  new FragmentProfilePlayer();
//                }}else{
//                    return  new FragmentOffline();
//                }
        }
        return  null;
    }

    @Override
    public int getCount() {
        return numTab;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Profile";
            case 1:
                return "List Match";


        }
        return null;
    }
}
