package com.aymanshehri.bakingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aymanshehri.bakingapp.MyViewPager;
import com.aymanshehri.bakingapp.R;
import com.aymanshehri.bakingapp.adapters.StepsAdapter;
import com.aymanshehri.bakingapp.models.Ingredient;
import com.aymanshehri.bakingapp.models.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepListActivity extends AppCompatActivity {

    @BindView(R.id.tv_recipe_name)
    TextView recipeName;
    @BindView(R.id.tv_recipe_servings)
    TextView recipeServings;
    @BindView(R.id.tv_ingredients)
    TextView recipeIngredients;
    @BindView(R.id.rv_recipe_steps)
    RecyclerView stepsRecyclerView;
    private boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_list);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        Recipe recipe = intent.getParcelableExtra("recipe");

        if (findViewById(R.id.fl_details_fragment) != null) {
            isTwoPane = true;
        }
        //populate the recycler view of the step List
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        stepsRecyclerView.setLayoutManager(linearLayoutManager);
        StepsAdapter stepsAdapter = new StepsAdapter(recipe.getSteps(), new StepsAdapter.onStepClickListener() {
            @Override
            public void onClick(int position) {
                if (isTwoPane) {
                    //todo link with page viewer
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("step", recipe.getSteps().get(position));
                    StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
                    stepDetailsFragment.setArguments(bundle);

                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction().replace(R.id.fl_details_fragment, stepDetailsFragment).commit();
                } else {
                    Intent intent = new Intent(getApplicationContext(), MyViewPager.class);
                    intent.putExtra("position", position);
                    intent.putParcelableArrayListExtra("steps", recipe.getSteps());
                    startActivity(intent);
                }
            }
        });
        stepsRecyclerView.setAdapter(stepsAdapter);
    }
}
