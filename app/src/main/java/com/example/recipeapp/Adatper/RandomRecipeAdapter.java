package com.example.recipeapp.Adatper;

import static android.media.CamcorderProfile.get;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Listeners.RecipeclickListener;
import com.example.recipeapp.Models.Recipe;
import com.example.recipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder>{
    Context context;
    List<Recipe> list;
    RecipeclickListener listener;

    public RandomRecipeAdapter(Context context, List<Recipe> list,RecipeclickListener listener) {
        this.context = context;
        this.list = list;
        this.listener=listener;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.textView_title.setText(list.get(position).title);
        holder.textView_title.setSelected(true);
        holder.textView_like2.setText(list.get(position).aggregateLikes+" likes");
        holder.textView_serving1.setText(list.get(position).servings+" Servings");
        holder.textView_time.setText(list.get(position).readyInMinutes+" Minutes");
        Picasso.get().load(list.get(position).image).into(holder.image_food);

        holder.radom_list_container.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.onrecipeclick(String.valueOf(list.get(holder.getAdapterPosition()).id));
        }
    });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class RandomRecipeViewHolder extends RecyclerView.ViewHolder{
    CardView radom_list_container;
    TextView textView_title,textView_serving1,textView_like2,textView_time;
    ImageView image_food;

    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        radom_list_container=itemView.findViewById(R.id.radom_list_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        textView_serving1=itemView.findViewById(R.id.textView_serving1);
        textView_like2=itemView.findViewById(R.id.textView_like2);
        textView_time=itemView.findViewById(R.id.textView_time);
        image_food=itemView.findViewById(R.id.image_food);
    }
}