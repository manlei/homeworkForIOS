package dsca.cs.nju.mytabmenudemo.classification;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import dsca.cs.nju.mytabmenudemo.R;

/**
 * Created by manlei on 2017/4/22.
 */

public class CuisineAdapter extends RecyclerView.Adapter<dsca.cs.nju.mytabmenudemo.classification.CuisineAdapter.ViewHolder>{
    private Context mcontext;
    private List<Cuisine> mCuisine;

    public CuisineAdapter(List<Cuisine> list){
        mCuisine = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mcontext == null)
            mcontext = parent.getContext();

        View view = LayoutInflater.from(mcontext).inflate(R.layout.cuisine_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext,mCuisine.get(viewHolder.getAdapterPosition()).getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mcontext,ClassificationDishes.class);
                intent.putExtra("kind",mCuisine.get(viewHolder.getAdapterPosition()).getName());
                mcontext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cuisine cuisine = mCuisine.get(position);
        holder.cuisineText.setText(cuisine.getName());
        Glide.with(mcontext).load(cuisine.getImageId()).into(holder.cuisineImage);
    }

    @Override
    public int getItemCount() {
        return mCuisine.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView cuisineImage;
        TextView cuisineText;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
            cuisineImage = (ImageView)itemView.findViewById(R.id.cuisine_image);
            cuisineText = (TextView)itemView.findViewById(R.id.cuisine_name);
        }
    }

}