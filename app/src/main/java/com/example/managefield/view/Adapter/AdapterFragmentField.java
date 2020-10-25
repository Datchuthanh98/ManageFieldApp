package com.example.managefield.view.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.managefield.view.Fragment.FragmentListBooking;
import com.example.managefield.view.Fragment.FragmentListMatch;
import com.example.managefield.view.Fragment.FragmentListTime;
import com.example.managefield.view.Fragment.FragmentProfileMyField;

public class AdapterFragmentField extends FragmentPagerAdapter {
    int numTab = 4;
    public AdapterFragmentField(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentListMatch();
            case 1:
                return new FragmentListBooking();
            case 2:
                return new FragmentListTime();
            case 3:
                return new FragmentProfileMyField();
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
                return "List Match";
            case 1:
                return "List Request";
            case 2:
                return "List Time";
            case 3:
                return "List Profile";
        }
        return null;
    }
}
