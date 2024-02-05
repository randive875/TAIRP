package com.example.recipeapp.Listeners;

import com.example.recipeapp.Models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListeners {
    void didfetch(RandomRecipeApiResponse response,String message);
    void diderr(String message);
}
