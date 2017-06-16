package dsca.cs.nju.mytabmenudemo.classification;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dsca.cs.nju.mytabmenudemo.R;

/**
 * Created by manlei on 2017/4/6.
 */


public class ClassificationFragment extends Fragment {

    //菜系
    private Cuisine[] cuisines={

            new Cuisine("breakfast",R.drawable.breakfast),

            new Cuisine("dessert",R.drawable.dessert),

            //泰式
            new Cuisine("thai",R.drawable.thai),

            //芝士
            new Cuisine("cheese",R.drawable.cheese),

            new Cuisine("taiwan",R.drawable.taiwan),

            new Cuisine("soup",R.drawable.soup),

            new Cuisine("beef",R.drawable.beef),

            new Cuisine("chicken",R.drawable.chicken),

            new Cuisine("italy",R.drawable.italy),

            new Cuisine("japan",R.drawable.japan),

            new Cuisine("american",R.drawable.american),

            new Cuisine("mexico",R.drawable.mexico)

    };
    private List<Cuisine> cuisineList = new ArrayList<Cuisine>();
    private CuisineAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_classification,null);
        initCuisine();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycle_view2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new CuisineAdapter(cuisineList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void initCuisine(){
        cuisineList.clear();
        for(int i  =0;i< cuisines.length;i++){
            cuisineList.add(cuisines[i]);
        }
    }
}
