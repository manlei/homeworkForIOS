package dsca.cs.nju.mytabmenudemo.dish;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import dsca.cs.nju.mytabmenudemo.R;
import dsca.cs.nju.mytabmenudemo.model.Dish;
import dsca.cs.nju.mytabmenudemo.service.ApiClient;

public class DishShow extends AppCompatActivity {

    @BindView(R.id.im_image_dish)
    ImageView image;

    @BindView(R.id.tv_title_dish)
    TextView title;

    @BindView(R.id.tv_label_dish)
    TextView label;

    @BindView(R.id.tv_description_dish)
    TextView description;

    private Dish dish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        dish = (Dish) getIntent().getSerializableExtra("dish");
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        Glide.with(DishShow.this)
                .load(ApiClient.getBaseImageUrl(dish.getImageUrl()))
                .into(image);
        title.setText(dish.getTitle());
        label.setText(dish.getLabel());
        description.setText(dish.getDescription());

    }

    public static void startActivityWithParameter(Context content, Dish dish){
        Intent intent = new Intent(content,DishShow.class);
        intent.putExtra("dish",dish);
        content.startActivity(intent);
    }
}
