package com.aymanshehri.bakingapp.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.aymanshehri.bakingapp.R;
import com.aymanshehri.bakingapp.models.Step;

import java.util.ArrayList;

public class StepDetailsActivity extends AppCompatActivity {

    private int position;
    private ArrayList<Step> steps;
    private int size;
    private FragmentManager fm;
    private StepDetailsFragment stepDetailsFragment;
    private Step step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        steps = intent.getParcelableArrayListExtra("steps");
        size = steps.size();
        step = steps.get(position);

        Bundle bundle = new Bundle();
        bundle.putParcelable("step", step);
        stepDetailsFragment = new StepDetailsFragment();
        stepDetailsFragment.setArguments(bundle);

        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fl_details_fragment, stepDetailsFragment).commit();
    }

    public void nextStep(){
        if(position+1<size){
            step = steps.get(position+1);
            Bundle bundle = new Bundle();
            bundle.putParcelable("step",step);
            stepDetailsFragment = new StepDetailsFragment();
            stepDetailsFragment.setArguments(bundle);
            fm.beginTransaction().replace(R.id.fl_details_fragment, stepDetailsFragment).commit();
            position++;
        }else{
            Toast.makeText(this,"This is the last step",Toast.LENGTH_SHORT).show();
        }
    }

    public void previousStep() {
        if(position>0){
            step = steps.get(position-1);
            Bundle bundle = new Bundle();
            bundle.putParcelable("step",step);
            stepDetailsFragment = new StepDetailsFragment();
            stepDetailsFragment.setArguments(bundle);
            fm.beginTransaction().replace(R.id.fl_details_fragment, stepDetailsFragment).commit();
            position--;
        }else{
            Toast.makeText(this,"This is the first step",Toast.LENGTH_SHORT).show();
        }
    }
}
