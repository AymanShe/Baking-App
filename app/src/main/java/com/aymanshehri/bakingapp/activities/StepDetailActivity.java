package com.aymanshehri.bakingapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.aymanshehri.bakingapp.adapters.MyViewPagerAdapter;
import com.aymanshehri.bakingapp.R;
import com.aymanshehri.bakingapp.models.Step;

import java.util.ArrayList;

public class StepDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        Intent intent = getIntent();
        ArrayList<Step> steps = intent.getParcelableArrayListExtra("steps");

        ViewPager viewPager = findViewById(R.id.view_pager);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager(),steps);
        viewPager.setAdapter(adapter);
    }
}
