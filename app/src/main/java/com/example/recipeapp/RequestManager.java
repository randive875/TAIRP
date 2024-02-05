package com.example.recipeapp;

import android.content.Context;


import com.example.recipeapp.Listeners.InstructionListener;
import com.example.recipeapp.Listeners.RandomRecipeResponseListeners;
import com.example.recipeapp.Listeners.RecipeDetailsListener;
import com.example.recipeapp.Listeners.SimilarRecipeListener;
import com.example.recipeapp.Models.InstructionsResponse;
import com.example.recipeapp.Models.RandomRecipeApiResponse;
import com.example.recipeapp.Models.RecipeDetailsResponse;
import com.example.recipeapp.Models.SimilarRecipeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/ ")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getRandomRecipe(RandomRecipeResponseListeners listener, List<String> tags) {
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe("10", "80097e7343c040b2a852a140c581f94f", tags);
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                if (!response.isSuccessful()) {
                    listener.diderr(response.message());
                    return;
                }
                listener.didfetch(response.body(), response.message());
            }


            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                listener.diderr(t.getMessage());
            }
        });
    }

    public void getRecipeDetails(RecipeDetailsListener listener, int id) {
        CallRecipeDeatils callRecipeDeatils = retrofit.create(CallRecipeDeatils.class);
        Call<RecipeDetailsResponse> call = callRecipeDeatils.callRecipeDeatils(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if (!response.isSuccessful()) {
                    listener.dider(response.message());
                    return;
                }
                listener.didfec(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listener.dider(t.getMessage());
            }
        });
    }

    public void getSimilarR(SimilarRecipeListener listener, int id) {
        CallsimilarRecipe callsimilarRecipe = retrofit.create(CallsimilarRecipe.class);
        Call<List<SimilarRecipeResponse>> call = callsimilarRecipe.callRecipesimilar(id, "5", context.getString(R.string.api_key));
        call.enqueue(new Callback<List<SimilarRecipeResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipeResponse>> call, Response<List<SimilarRecipeResponse>> response) {
                if (!response.isSuccessful()) {
                    listener.dider(response.message());
                    return;
                }
                listener.didfec(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipeResponse>> call, Throwable t) {
                listener.dider(t.getMessage());
            }
        });
    }
    public void getInstructions(InstructionListener listener,int id){
        CallInstruction callInstruction=retrofit.create(CallInstruction.class);
        Call<List<InstructionsResponse>> call=callInstruction.callInstructions(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<List<InstructionsResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionsResponse>> call, Response<List<InstructionsResponse>> response) {
                if (!response.isSuccessful()){
                    listener.er(response.message());
                    return;
                }
                listener.fe(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<List<InstructionsResponse>> call, Throwable t) {
                listener.er(t.getMessage());
            }
        });
    }


    private interface CallRandomRecipes {
        @GET("recipes/random")
        Call<RandomRecipeApiResponse> callRandomRecipe(
                @Query("number") String number,
                @Query("apiKey") String apiKey,
                @Query("tags") List<String> tags
        );
    }

    private interface CallRecipeDeatils {
        @GET("recipes/{id}/information")
        Call<RecipeDetailsResponse> callRecipeDeatils(
                @Path("id") int id,
                @Query("apiKey") String apiKey

        );
    }

    private interface CallsimilarRecipe {
        @GET("recipes/{id}/similar")
        Call<List<SimilarRecipeResponse>> callRecipesimilar(
                @Path("id") int id,
                @Query("number") String number,
                @Query("apiKey") String apiKey
        );
    }
    private interface CallInstruction{
        @GET("recipes/{id}/analyzedInstructions")
        Call<List<InstructionsResponse>>  callInstructions(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }


}
