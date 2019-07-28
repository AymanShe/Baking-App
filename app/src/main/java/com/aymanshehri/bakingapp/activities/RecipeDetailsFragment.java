package com.aymanshehri.bakingapp.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aymanshehri.bakingapp.R;
import com.aymanshehri.bakingapp.adapters.StepsAdapter;
import com.aymanshehri.bakingapp.models.Ingredient;
import com.aymanshehri.bakingapp.models.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailsFragment extends Fragment {

    @BindView(R.id.tv_recipe_name)
    TextView recipeName;
    @BindView(R.id.tv_recipe_servings)
    TextView recipeServings;
    @BindView(R.id.tv_ingredients)
    TextView recipeIngredients;
    @BindView(R.id.rv_recipe_steps)
    RecyclerView stepsRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        ButterKnife.bind(this, view);

        Recipe recipe = getArguments().getParcelable("recipe");


        //name
        recipeName.setText(recipe.getName());

        //servings
        String recipeServingsText = "Enough for " + recipe.getServings();
        recipeServings.setText(recipeServingsText);

        //ingredient
        for (Ingredient ingredient : recipe.getIngredients()) {
            recipeIngredients.append(ingredient.getMeasure());
            recipeIngredients.append(" ");
            recipeIngredients.append(ingredient.getQuantity() + "");
            recipeIngredients.append(" ");
            recipeIngredients.append(ingredient.getIngredient());
            recipeIngredients.append("\n");
        }

        //steps
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        stepsRecyclerView.setLayoutManager(linearLayoutManager);
        StepsAdapter stepsAdapter = new StepsAdapter(getActivity(), recipe.getSteps());
        stepsRecyclerView.setAdapter(stepsAdapter);


        return view;
    }
}
