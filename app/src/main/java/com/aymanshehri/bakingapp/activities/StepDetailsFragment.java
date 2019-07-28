package com.aymanshehri.bakingapp.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.aymanshehri.bakingapp.R;
import com.aymanshehri.bakingapp.models.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepDetailsFragment extends Fragment {

    @BindView(R.id.tv_description)
    TextView description;
    @BindView(R.id.btn_next)
    Button next;
    @BindView(R.id.btn_previous)
    Button previous;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_details,container,false);
        ButterKnife.bind(this, view);
        Step step = getArguments().getParcelable("step");


        Bundle bundle = new Bundle();
        bundle.putString("video", step.getVideoURL());
        bundle.putString("image", step.getThumbnailURL());
        VideoOrImageFragment videoOrImageFragment = new VideoOrImageFragment();
        videoOrImageFragment.setArguments(bundle);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().add(R.id.fl_video_or_image, videoOrImageFragment).commit();




        description.setText(step.getDescription());



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StepDetailsActivity)getActivity()).nextStep();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((StepDetailsActivity)getActivity()).previousStep();
            }
        });

        return view;
    }
}
