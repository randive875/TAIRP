package com.example.recipeapp.Listeners;

import com.example.recipeapp.Models.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipeListener {
        void didfec(List<SimilarRecipeResponse> responses,String message);
        void dider(String message);
}
