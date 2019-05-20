package com.aymanshehri.bakingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aymanshehri.bakingapp.R;
import com.aymanshehri.bakingapp.adapters.StepsAdapter;
import com.aymanshehri.bakingapp.models.Ingredient;
import com.aymanshehri.bakingapp.models.Recipe;

public class RecipeDetailsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);


        Recipe recipe = getArguments().getParcelable("recipe");


        //name
        TextView recipeName = view.findViewById(R.id.tv_recipe_name);//todo use butterknife
        recipeName.setText(recipe.getName());

        //servings
        TextView recipeServings = view.findViewById(R.id.tv_recipe_servings);
        String recipeServingsText = "Enough for " + recipe.getServings();
        recipeServings.setText(recipeServingsText);

        //ingredient
        TextView recipeIngredients = view.findViewById(R.id.tv_ingredients);
        for (Ingredient ingredient:  recipe.getIngredients()) {
            recipeIngredients.append(ingredient.getMeasure());
            recipeIngredients.append(" ");
            recipeIngredients.append(ingredient.getQuantity()+"");
            recipeIngredients.append(" ");
            recipeIngredients.append(ingredient.getIngredient());
            recipeIngredients.append("\n");
        }

        //steps
        RecyclerView stepsRecyclerView = view.findViewById(R.id.rv_recipe_steps);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        stepsRecyclerView.setLayoutManager(linearLayoutManager);
        StepsAdapter stepsAdapter = new StepsAdapter(getActivity(), recipe.getSteps());
        stepsRecyclerView.setAdapter(stepsAdapter);






        return view;
    }
}
