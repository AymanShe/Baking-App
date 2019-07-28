package com.aymanshehri.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aymanshehri.bakingapp.R;
import com.aymanshehri.bakingapp.activities.StepDetailsActivity;
import com.aymanshehri.bakingapp.models.Step;

import java.util.ArrayList;
import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepViewHolder>    {

    Context context;
    ArrayList<Step> stepList;

    public StepsAdapter(Context context, ArrayList<Step> stepList) {
        this.context = context;
        this.stepList = stepList;
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
        TextView stepName;
        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            stepName = itemView.findViewById(R.id.tv_step_item);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, StepDetailsActivity.class);
                    intent.putExtra("position",getAdapterPosition());
                    intent.putParcelableArrayListExtra("steps",stepList);
                    context.startActivity(intent);
                }
            });

        }
    }
}
