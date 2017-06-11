package dsca.cs.nju.mytabmenudemo.notification;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dsca.cs.nju.mytabmenudemo.R;
import dsca.cs.nju.mytabmenudemo.message.MessageView;

/**
 * Created by manlei on 2017/4/22.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{
    private Context mcontext;
    private List<Notification> mNotification;

    public NotificationAdapter(List<Notification> list){
        mNotification = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mcontext == null)
            mcontext = parent.getContext();

        View view = LayoutInflater.from(mcontext).inflate(R.layout.notification_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                MessageView.startActivityWithParameter(mcontext,mNotification.get(position));
                Toast.makeText(mcontext, mNotification.get(viewHolder.getAdapterPosition()).getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Notification notification = mNotification.get(position);
        holder.notificationTitle.setText(notification.getTitle());
        holder.notificationContent.setText(notification.getContent());
    }

    @Override
    public int getItemCount() {
        return mNotification.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView notificationTitle;
        TextView notificationContent;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
            notificationTitle = (TextView) itemView.findViewById(R.id.textview_title);
            notificationContent = (TextView)itemView.findViewById(R.id.textview_content);
        }
    }

}

//
//public class DishAdapter extends RecyclerView.Adapter<dsca.cs.nju.mytabmenudemo.home.DishAdapter.ViewHolder> {
//    private Context mcontext;
//    private List<Fruit> mFruitList;
//
//    static class ViewHolder extends RecyclerView.ViewHolder{
//        CardView cardView;
//        ImageView fruitImage;
//        TextView fruitName;
//
//        public ViewHolder(View view){
//            super(view);
//            cardView  = (CardView)view;
//            fruitImage = (ImageView)view.findViewById(R.id.fruit_image);
//            fruitName = (TextView)view.findViewById(R.id.fruit_name);
//        }
//    }
//
//    public DishAdapter(List<Fruit> fruitList){
//        mFruitList = fruitList;
//    }
//    public dsca.cs.nju.mytabmenudemo.home.DishAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
//        if(mcontext == null)
//            mcontext = parent.getContext();
//        View view = LayoutInflater.from(mcontext).inflate(R.layout.fruit_item,parent,false);
//
//        final dsca.cs.nju.mytabmenudemo.home.DishAdapter.ViewHolder holder = new dsca.cs.nju.mytabmenudemo.home.DishAdapter.ViewHolder(view);
//        holder.cardView.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                Toast.makeText(mcontext,"click",Toast.LENGTH_SHORT).show();
////                int position = holder.getAdapterPosition();
////                Fruit fruit = mFruitList.get(position);
////                Intent intent = new Intent(mcontext,FruitActivity.class);
////                intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
////                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getImageId());
////                mcontext.startActivity(intent);
//            }
//        });
//        return holder;
//        //return new ViewHolder(view);
//    }
//    public void onBindViewHolder(dsca.cs.nju.mytabmenudemo.home.DishAdapter.ViewHolder holder, int position){
//        Fruit fruit = mFruitList.get(position);
//        holder.fruitName.setText(fruit.getName());
//        Glide.with(mcontext).load(fruit.getImageId()).into(holder.fruitImage);
//    }
//    public int getItemCount(){
//        return mFruitList.size();
//    }
//
//}
