package dsca.cs.nju.mytabmenudemo.notification;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dsca.cs.nju.mytabmenudemo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {
    private Notification[] notifications = {new Notification("breakfast", "Do you want to eat breakfeast?"),
            new Notification("Dinner", "How about have a dinner?"),
            new Notification("watermelon","Do you want to have watermelon?"),
            new Notification("Business","How to enjoy your Business Lunch?")
//            new Notification("Grape","R.drawable.grape'"),
//            new Notification("Pineapple","R.drawable.pineapple"),
//            new Notification("Strawberry","R.drawable.strawberry"),
//            new Notification("Mango","R.drawable.mango")
    };
    private List<Notification> notificationList = new ArrayList<Notification>();
    private NotificationAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_notification, container, false);

        initNotification();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycle_view3);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new NotificationAdapter(notificationList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swiperefresh_notification);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
                                                    public void onRefresh(){
                                                        refreshNotifications();
                                                        //Toast.makeText(MainActivity.this,"refresh",Toast.LENGTH_SHORT).show();
                                                    }
                                                }
        );

        return view;
    }

    private void initNotification(){
        notificationList.clear();
        for(int i  =0;i< 4;i++){
            Random random = new Random();
            int index = random.nextInt(notifications.length);
            notificationList.add(notifications[index]);
        }
    }

    private void refreshNotifications(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(3000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initNotification();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

        }).start();
    }

}
