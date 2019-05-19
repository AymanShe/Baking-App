package com.aymanshehri.bakingapp.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.aymanshehri.bakingapp.R;
import com.aymanshehri.bakingapp.adapters.RecipesAdapter;
import com.aymanshehri.bakingapp.WebService;
import com.aymanshehri.bakingapp.models.Recipe;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    final String TAG = "MyTag";
    private List<Recipe> recipes;
    RecyclerView recyclerView;
    private RecipesAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //todo  ButterKnife.bind(this);

        getRecipes();

        recyclerView = findViewById(R.id.rv_recipe);//todo use butterknife
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //todo restore instance state
//        if (instanceState != null)
//            linearLayoutManager.onRestoreInstanceState(instanceState);
        recyclerView.setLayoutManager(linearLayoutManager);
        recipeAdapter = new RecipesAdapter(this);
        recyclerView.setAdapter(recipeAdapter);


    }

    private void getRecipes(){
        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebService webservice = retrofit.create(WebService.class);

        Call<List<Recipe>> call = webservice.getRecipes();

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                //remove loading panel
                //assign to recipes
                //update recycler view
                recipes = response.body();
                recipeAdapter.notifyChange(recipes);
            }

            @Override
            public void onFailure(@NonNull Call<List<Recipe>> call, Throwable t) {
                //show message
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
