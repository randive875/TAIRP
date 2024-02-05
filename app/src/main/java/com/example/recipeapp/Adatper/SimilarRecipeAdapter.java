package com.example.recipeapp.Adatper;

import static android.media.CamcorderProfile.get;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Listeners.RecipeclickListener;
import com.example.recipeapp.Models.SimilarRecipeResponse;
import com.example.recipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipeAdapter extends RecyclerView.Adapter<SimilarRecipeViewHolder>{
    Context context;
    List<SimilarRecipeResponse> list;
    RecipeclickListener listener;

    public SimilarRecipeAdapter(Context context, List<SimilarRecipeResponse> list, RecipeclickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SimilarRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimilarRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_similar_recipe,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull SimilarRecipeViewHolder holder, int position) {
        holder.similar_title.setText(list.get(position).title);
        holder.similar_title.setSelected(true);
        holder.similar_serving.setText(list.get(position).servings+ " Persons");
        Picasso.get().load("https://spoonacular.com/productImages/"+list.get(position).id+"-312x231."+list.get(position).imageType).into(holder.image_similar);
        holder.similar_holder.setOnClickListener(new View.OnClickListener() {
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
class SimilarRecipeViewHolder extends RecyclerView.ViewHolder{
    CardView similar_holder;
    TextView similar_title,similar_serving;
    ImageView image_similar;

    public SimilarRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        similar_holder=itemView.findViewById(R.id.similar_holder);
        similar_title=itemView.findViewById(R.id.similar_title);
        similar_serving=itemView.findViewById(R.id.similar_serving);
        image_similar=itemView.findViewById(R.id.image_similar);

    }
}