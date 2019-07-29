package com.aymanshehri.bakingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.aymanshehri.bakingapp.R;
import com.aymanshehri.bakingapp.models.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepViewHolder>    {

    private onStepClickListener callBack;
    private ArrayList<Step> stepList;

    public StepsAdapter(ArrayList<Step> stepList, onStepClickListener callBack) {
        this.stepList = stepList;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View movieView = layoutInflater.inflate(R.layout.step_item, viewGroup, false);
        return new StepsAdapter.StepViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder stepViewHolder, int i) {
        stepViewHolder.stepName.setText(stepList.get(i).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_step_item)
        TextView stepName;
        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.onClick(getAdapterPosition());
                }
            });
        }
    }

    public interface onStepClickListener{
        public void onClick(int position);
    }
}
