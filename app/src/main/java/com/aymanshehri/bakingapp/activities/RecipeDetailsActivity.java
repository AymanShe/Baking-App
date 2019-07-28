package com.aymanshehri.bakingapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.aymanshehri.bakingapp.R;
import com.aymanshehri.bakingapp.models.Recipe;

public class RecipeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        Intent intent = getIntent();
        Recipe recipe = intent.getParcelableExtra("recipe");

        Bundle bundle = new Bundle();
        bundle.putParcelable("recipe",recipe);
        RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
        recipeDetailsFragment.setArguments(bundle);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fl_main_fragment, recipeDetailsFragment).commit();
    }
}
