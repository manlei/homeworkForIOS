package dsca.cs.nju.mytabmenudemo.home;

import android.content.Context;
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
import dsca.cs.nju.mytabmenudemo.dish.DishShow;
import dsca.cs.nju.mytabmenudemo.model.Dish;
import dsca.cs.nju.mytabmenudemo.service.ApiClient;

/**
 * Created by manlei on 2017/3/28.
 */

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {
    private Context context;
    private List<Dish> dishList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView dishImage;
        TextView dishTitle;

        public ViewHolder(View view){
            super(view);
            cardView  = (CardView)view;
            dishImage = (ImageView)view.findViewById(R.id.image_dish_item);
            dishTitle = (TextView)view.findViewById(R.id.title_dish_item);
        }
    }

    public DishAdapter(List<Dish> dishList){
        this.dishList = dishList;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(context == null)
            context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.dish_item,parent,false);

        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                DishShow.startActivityWithParameter(context,dishList.get(position));
                Toast.makeText(context,"click",Toast.LENGTH_SHORT).show();
//                int position = holder.getAdapterPosition();
//                Fruit fruit = dishList.get(position);
//                Intent intent = new Intent(context,FruitActivity.class);
//                intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
//                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getImageId());
//                context.startActivity(intent);
            }
        });
        return holder;
        //return new ViewHolder(view);
    }
    public void onBindViewHolder(ViewHolder holder,int position){
        Dish dish = dishList.get(position);
        holder.dishTitle.setText(dish.getTitle());
        Glide.with(context).load(ApiClient.getBaseImageUrl(dish.getImageUrl())).into(holder.dishImage);
    }
    public int getItemCount(){
        return dishList.size();
    }

}
