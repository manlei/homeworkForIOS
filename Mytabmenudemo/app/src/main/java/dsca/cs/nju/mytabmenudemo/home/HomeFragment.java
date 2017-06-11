package dsca.cs.nju.mytabmenudemo.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dsca.cs.nju.mytabmenudemo.R;
import dsca.cs.nju.mytabmenudemo.model.Dish;
import dsca.cs.nju.mytabmenudemo.publish.PublishDish;
import dsca.cs.nju.mytabmenudemo.service.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by manlei on 2017/4/6.
 */

public class HomeFragment extends Fragment {


    private static final String TAG = "HomeFragment";

    private List<Dish> dishList = new ArrayList<Dish>();

    private DishAdapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home,null);

        initRandomList();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycle_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new DishAdapter(dishList);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab_home);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PublishDish.class);
                getActivity().startActivity(intent);
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swiperefresh_home);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
                                                    public void onRefresh(){
                                                        refreshDishes();
                                                        //Toast.makeText(MainActivity.this,"refresh",Toast.LENGTH_SHORT).show();
                                                    }
                                                }
        );


        return view;
    }

    /**
     * 随机获取数据，更新UI
     */
    private void refreshDishes(){

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
                        //initRandomList();
                        //get data from server
                        ApiClient.getUserService().getRandomDishList("random").enqueue(new Callback<List<Dish>>() {
                            @Override
                            public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {
                                dishList.clear();
                                Toast.makeText(getActivity().getBaseContext(),"获取数据成功",Toast.LENGTH_SHORT).show();
                                for (int i = 0; i < response.body().size(); i++) {
                                    Log.e(TAG,response.body().get(i).getTitle());
                                }

                                dishList.addAll(response.body());
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<List<Dish>> call, Throwable t) {
                                Toast.makeText(getActivity().getBaseContext(),"获取数据失败",Toast.LENGTH_SHORT).show();
                            }
                        });

                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

        }).start();
    }

    /**
     * 初始化显示列表
     */
    private void initRandomList(){
        dishList.clear();

        new Thread() {
            @Override
            public void run(){
                ApiClient.getUserService().getRandomDishList("random").enqueue(new Callback<List<Dish>>() {
                    @Override
                    public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {
                        Toast.makeText(getActivity().getBaseContext(),"初始化获取数据成功",Toast.LENGTH_SHORT).show();
                        dishList.clear();
                        dishList.addAll(response.body());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Dish>> call, Throwable t) {
                        Toast.makeText(getActivity().getBaseContext(),"初始化获取数据失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();
    }
    
}
