package com.example.recipeapp.Adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Models.ExtendedIngredient;
import com.example.recipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder> {
    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_food_ing,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.ing_name.setText(list.get(position).name);
        holder.text_ing_quantity.setText(list.get(position).original);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.image_ing);
        holder.ing_name.setSelected(true);
        holder.text_ing_quantity.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class IngredientsViewHolder extends RecyclerView.ViewHolder {
    TextView text_ing_quantity,ing_name;
    ImageView image_ing;
    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        text_ing_quantity=itemView.findViewById(R.id.text_ing_quantity);
        ing_name=itemView.findViewById(R.id.ing_name);
        image_ing=itemView.findViewById(R.id.image_ing);


    }
}