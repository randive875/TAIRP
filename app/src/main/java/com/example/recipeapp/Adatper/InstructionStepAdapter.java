package com.example.recipeapp.Adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Models.Step;
import com.example.recipeapp.R;

import java.util.List;

public class InstructionStepAdapter extends RecyclerView.Adapter<InstructionStepViewHolder>{
    Context context;
    List<Step> list;

    public InstructionStepAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructons_step,parent,false)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepViewHolder holder, int position) {
        holder.step_number.setText(String.valueOf(list.get(position).number));
        holder.step_number_title.setText(list.get(position).step);

        holder.recycle_ingedients.setHasFixedSize(true);
        holder.recycle_ingedients.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        InstructionsIngredientAdapter instructionsIngredientAdapter = new InstructionsIngredientAdapter(context,list.get(position).ingredients);
        holder.recycle_ingedients.setAdapter(instructionsIngredientAdapter);
        holder.recycle_equiment.setHasFixedSize(true);
        holder.recycle_equiment.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        InstructionsEquimentAdapter instructionsEquimentAdapter=new InstructionsEquimentAdapter(context,list.get(position).equipment);
        holder.recycle_equiment.setAdapter(instructionsEquimentAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionStepViewHolder extends RecyclerView.ViewHolder{
    TextView step_number,step_number_title;
    RecyclerView recycle_equiment,recycle_ingedients;
    public InstructionStepViewHolder(@NonNull View itemView) {
        super(itemView);
        step_number=itemView.findViewById(R.id.step_number);
        step_number_title=itemView.findViewById(R.id.step_number_title);
        recycle_equiment=itemView.findViewById(R.id.recycle_equiment);
        recycle_ingedients=itemView.findViewById(R.id.recycle_ingedients);
    }
}