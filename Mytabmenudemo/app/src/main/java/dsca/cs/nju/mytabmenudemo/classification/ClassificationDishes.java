package dsca.cs.nju.mytabmenudemo.classification;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dsca.cs.nju.mytabmenudemo.R;
import dsca.cs.nju.mytabmenudemo.home.DishAdapter;
import dsca.cs.nju.mytabmenudemo.model.Dish;
import dsca.cs.nju.mytabmenudemo.service.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassificationDishes extends AppCompatActivity {

    @BindView(R.id.tb_classification_dishes)
    Toolbar toolbar;

    @BindView(R.id.swiperefresh_classification_dishes)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recyclerview_classification_dishes)
    RecyclerView recyclerView;

    private List<Dish> dishList = new ArrayList<Dish>();

    private DishAdapter adapter;

    private String kind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification_dishes);
        ButterKnife.bind(this);

        kind = getIntent().getStringExtra("kind");

        initRandomList();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getBaseContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new DishAdapter(dishList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRandomList();
            }
        });

    }


    /**
     * 初始化显示列表
     */
    private void initRandomList(){
        dishList.clear();

        new Thread() {
            @Override
            public void run(){
                ApiClient.getUserService().getRandomDishList(kind).enqueue(new Callback<List<Dish>>() {
                    @Override
                    public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {
                        Toast.makeText(getBaseContext(),"初始化获取数据成功",Toast.LENGTH_SHORT).show();
                        dishList.clear();
                        dishList.addAll(response.body());
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<List<Dish>> call, Throwable t) {
                        Toast.makeText(getBaseContext(),"初始化获取数据失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();
    }
}
