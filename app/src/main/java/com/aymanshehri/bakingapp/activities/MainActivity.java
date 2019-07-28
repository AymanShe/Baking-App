package com.aymanshehri.bakingapp.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aymanshehri.bakingapp.R;
import com.aymanshehri.bakingapp.adapters.RecipesAdapter;
import com.aymanshehri.bakingapp.WebService;
import com.aymanshehri.bakingapp.models.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    final String TAG = "MyTag";
    private List<Recipe> recipes;
    @BindView(R.id.rv_recipe)
    RecyclerView recyclerView;
    private RecipesAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getRecipes();
        //todo findout of it is a tablet and assign number of columns to 2
        int numberOfColumns = 2;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, numberOfColumns);
        //todo restore instance state
//        if (instanceState != null)
//            linearLayoutManager.onRestoreInstanceState(instanceState);
        recyclerView.setLayoutManager(gridLayoutManager);
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
                //todo remove loading panel
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
