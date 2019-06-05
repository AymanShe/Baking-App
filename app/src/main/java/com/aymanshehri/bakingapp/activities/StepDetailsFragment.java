package com.aymanshehri.bakingapp.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aymanshehri.bakingapp.R;
import com.aymanshehri.bakingapp.models.Step;

public class StepDetailsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_details,container,false);

        Step step = getArguments().getParcelable("step");


        Bundle bundle = new Bundle();
        bundle.putString("video", step.getVideoURL());
        bundle.putString("image", step.getThumbnailURL());
        VideoOrImageFragment videoOrImageFragment = new VideoOrImageFragment();
        videoOrImageFragment.setArguments(bundle);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().add(R.id.fl_video_or_image, videoOrImageFragment).commit();



        TextView description = view.findViewById(R.id.tv_description);
        description.setText(step.getDescription());

        Button next = view.findViewById(R.id.btn_next);
        Button previous = view.findViewById(R.id.btn_previous);

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
