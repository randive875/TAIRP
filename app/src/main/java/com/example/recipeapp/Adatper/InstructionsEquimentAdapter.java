package com.example.recipeapp.Adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Models.Equipment;
import com.example.recipeapp.Models.Ingredient;
import com.example.recipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionsEquimentAdapter extends RecyclerView.Adapter<InstructionEquimentViewHolder> {
    Context context;
    List<Equipment>  list;

    public InstructionsEquimentAdapter(Context context, List<Equipment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionEquimentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionEquimentViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_step_items,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull InstructionEquimentViewHolder holder, int position) {
        holder.step_items.setText(list.get(position).name);
        holder.step_items.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/equipment_100x100/"+list.get(position).image).into(holder.image_instruction_items);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionEquimentViewHolder extends RecyclerView.ViewHolder {
    ImageView image_instruction_items;
    TextView step_items;
    public InstructionEquimentViewHolder(@NonNull View itemView) {
        super(itemView);
        image_instruction_items=itemView.findViewById(R.id.image_instruction_items);
        step_items=itemView.findViewById(R.id.step_items);
    }
}