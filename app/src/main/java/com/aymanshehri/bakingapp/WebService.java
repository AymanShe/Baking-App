package com.aymanshehri.bakingapp;

import com.aymanshehri.bakingapp.models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebService {

    String BASE_URL = "https://go.udacity.com/android-baking-app-json/";

    @GET(BASE_URL)
    Call<List<Recipe>> getRecipes();
}
