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
import com.aymanshehri.bakingapp.activities.RecipeDetailsActivity;
import com.aymanshehri.bakingapp.activities.RecipeDetailsFragment;
import com.aymanshehri.bakingapp.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>   {

    Context context;
    List<Recipe> recipes;

    public RecipesAdapter(Context context) {
        this.context = context;
        this.recipes = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View movieView = layoutInflater.inflate(R.layout.recipe_item, viewGroup, false);
        return new RecipeViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {
        recipeViewHolder.recipeName.setText(recipes.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void notifyChange(List<Recipe> recipes){
        this.recipes.clear();
        this.recipes.addAll(recipes);
        notifyDataSetChanged();
    }
    class RecipeViewHolder extends RecyclerView.ViewHolder {
        final TextView recipeName;

        RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.tv_recipe_name_label);//todo use butter knife

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        Intent intent = new Intent(context, RecipeDetailsActivity.class);
                        intent.putExtra("recipe",recipes.get(getAdapterPosition()));
                        context.startActivity(intent);
                    }
                }
            );
        }
    }
}
