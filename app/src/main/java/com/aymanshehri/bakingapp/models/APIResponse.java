package com.aymanshehri.bakingapp.models;

import com.google.gson.JsonArray;

public class APIResponse {
    private int id;
    private String name;
    private JsonArray ingredients;
    private JsonArray steps;
    private int servings;
    private String image;
}
