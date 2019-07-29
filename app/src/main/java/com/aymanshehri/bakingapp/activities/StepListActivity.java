package com.aymanshehri.bakingapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.aymanshehri.bakingapp.adapters.MyViewPagerAdapter;
import com.aymanshehri.bakingapp.R;
import com.aymanshehri.bakingapp.adapters.StepsAdapter;
import com.aymanshehri.bakingapp.models.Ingredient;
import com.aymanshehri.bakingapp.models.Recipe;
import com.aymanshehri.bakingapp.models.Step;

import java.util.ArrayList;

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

    Recipe recipe;
    ArrayList<Step> steps;
    SharedPreferences sharedPreferences;
    private ViewPager viewPager;
    private boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_list);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        recipe = intent.getParcelableExtra("recipe");
        steps = recipe.getSteps();
        if (findViewById(R.id.view_pager) != null) {
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
        StepsAdapter stepsAdapter = new StepsAdapter(steps, new StepsAdapter.onStepClickListener() {
            @Override
            public void onClick(int position) {
                if (isTwoPane) {
                    viewPager.setCurrentItem(position);
                } else {
                    Intent intent = new Intent(getApplicationContext(), StepDetailActivity.class);
                    intent.putExtra("position", position);
                    intent.putParcelableArrayListExtra("steps", recipe.getSteps());
                    startActivity(intent);
                }
            }
        });
        stepsRecyclerView.setAdapter(stepsAdapter);


        if (isTwoPane) {
            viewPager = findViewById(R.id.view_pager);
            MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager(), steps);
            viewPager.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_option_menu, menu);

        sharedPreferences = getSharedPreferences("com.aymanshehri.bakingapp.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE);
        if ((sharedPreferences.getInt("recipe_id", 0) == recipe.getId())) {
            menu.findItem(R.id.mi_favorite).setIcon(R.drawable.ic_favorite);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int clickedItemId = item.getItemId();

        switch (clickedItemId) {
            case R.id.mi_favorite:
                boolean isFavorite;
                if (sharedPreferences.getInt("recipe_id", -1) == recipe.getId())
                    isFavorite = true;
                else
                    isFavorite = false;

                if (isFavorite) {
                    sharedPreferences.edit()
                            .remove("recipe_id")
                            .remove("widget_title")
                            .remove("widget_ingredients").apply();
                    item.setIcon(R.drawable.ic_favorite_border);
                    Toast.makeText(this, "Recipe Removed Successfully From Widget", Toast.LENGTH_SHORT).show();
                } else {
                    sharedPreferences.edit()
                            .putInt("recipe_id", recipe.getId())
                            .putString("widget_title", recipe.getName())
                            .putString("widget_ingredients", recipeIngredients.getText().toString()).apply();
                    item.setIcon(R.drawable.ic_favorite);
                    Toast.makeText(this, "Recipe Added Successfully To Widget", Toast.LENGTH_SHORT).show();
                }

                //todo apply chamges to widget
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
