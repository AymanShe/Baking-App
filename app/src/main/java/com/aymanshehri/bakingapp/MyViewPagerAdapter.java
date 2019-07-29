package com.aymanshehri.bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.aymanshehri.bakingapp.activities.StepDetailsFragment;
import com.aymanshehri.bakingapp.models.Step;

import java.util.ArrayList;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<Step> steps;

    public MyViewPagerAdapter(FragmentManager fm, ArrayList<Step> steps) {
        super(fm);
        this.steps = steps;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("step", steps.get(position));
        StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
        stepDetailsFragment.setArguments(bundle);
        return stepDetailsFragment;
    }

    @Override
    public int getCount() {
        return steps.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return steps.get(position).getShortDescription();
    }
}
