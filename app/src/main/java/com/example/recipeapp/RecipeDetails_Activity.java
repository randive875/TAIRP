package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipeapp.Adatper.IngredientsAdapter;
import com.example.recipeapp.Adatper.InstructionAdapter;
import com.example.recipeapp.Adatper.SimilarRecipeAdapter;
import com.example.recipeapp.Listeners.InstructionListener;
import com.example.recipeapp.Listeners.RecipeDetailsListener;
import com.example.recipeapp.Listeners.RecipeclickListener;
import com.example.recipeapp.Listeners.SimilarRecipeListener;
import com.example.recipeapp.Models.InstructionsResponse;
import com.example.recipeapp.Models.RecipeDetailsResponse;
import com.example.recipeapp.Models.SimilarRecipeResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetails_Activity extends AppCompatActivity {
    int id;
    TextView Recipe_name,meal_soure,meal_summary;
    ImageView meal_image;
    RecyclerView recycler_ing,recycle_summary,recycle_instruction;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter  ingredientsAdapter;
    SimilarRecipeAdapter similarRecipeAdapter;

    InstructionAdapter instructionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        findview();
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager=new RequestManager(this);
        manager.getRecipeDetails(recipeDeatilsListener,id);
        manager.getSimilarR(similarRecipeLister,id);
        manager.getInstructions(instructionListener,id);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading Details...");
        dialog.show();

    }

    private void findview() {
        Recipe_name=findViewById(R.id. Recipe_name);
        meal_soure=findViewById(R.id. meal_soure);
        meal_summary=findViewById(R.id. meal_summary);
        meal_image=findViewById(R.id. meal_image);
        recycler_ing=findViewById(R.id.  recycler_ing);
        recycle_summary=findViewById(R.id.recycle_summary);
        recycle_instruction=findViewById(R.id.recycle_instruction);

    }
    private final RecipeDetailsListener recipeDeatilsListener=new RecipeDetailsListener() {
        @Override
        public void didfec(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            Recipe_name.setText(response.title);
            meal_soure.setText(response.sourceName);
            meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(meal_image);
            recycler_ing.setHasFixedSize(true);
            recycler_ing.setLayoutManager(new LinearLayoutManager(RecipeDetails_Activity.this,LinearLayoutManager.HORIZONTAL,false));
            ingredientsAdapter = new IngredientsAdapter(RecipeDetails_Activity.this,response.extendedIngredients);
            recycler_ing.setAdapter(ingredientsAdapter);
        }

        @Override
        public void dider(String message) {
            Toast.makeText(RecipeDetails_Activity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private final SimilarRecipeListener similarRecipeLister= new SimilarRecipeListener() {
        @Override
        public void didfec(List<SimilarRecipeResponse> responses, String message) {
            recycle_summary.setHasFixedSize(true);
            recycle_summary.setLayoutManager(new LinearLayoutManager(RecipeDetails_Activity.this,LinearLayoutManager.HORIZONTAL,false));
            similarRecipeAdapter = new SimilarRecipeAdapter(RecipeDetails_Activity.this,responses,recipeclickListener);
            recycle_summary.setAdapter(similarRecipeAdapter);
        }

        @Override
        public void dider(String message) {
            Toast.makeText(RecipeDetails_Activity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
    private final RecipeclickListener recipeclickListener = new RecipeclickListener() {
        @Override
        public void onrecipeclick(String id) {
            Toast.makeText(RecipeDetails_Activity.this, id, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RecipeDetails_Activity.this,RecipeDetails_Activity.class)
                    .putExtra("id",id));

        }
    };
    private final InstructionListener instructionListener=new InstructionListener() {
        @Override
        public void fe(List<InstructionsResponse> response, String message) {
            recycle_instruction.setHasFixedSize(true);
            recycle_instruction.setLayoutManager(new LinearLayoutManager(RecipeDetails_Activity.this,LinearLayoutManager.VERTICAL,false));
            instructionAdapter=new InstructionAdapter(RecipeDetails_Activity.this,response);
            recycle_instruction.setAdapter(instructionAdapter);
        }

        @Override
        public void er(String message) {

        }
    };

}


