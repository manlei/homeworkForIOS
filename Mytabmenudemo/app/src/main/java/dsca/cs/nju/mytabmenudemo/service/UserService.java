package dsca.cs.nju.mytabmenudemo.service;

import java.util.List;

import dsca.cs.nju.mytabmenudemo.model.Dish;
import dsca.cs.nju.mytabmenudemo.model.User;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by manlei on 2017/5/31.
 * 说明：
 */

public interface UserService {

    //login and register
    @POST("user")
    Call<User> signIn(@Body User user);


    @GET("dishes")
    Call<List<Dish>> getRandomDishList(@Query("parameter") String parameter);

    //增加一道菜
    @Multipart
    @POST("dishes")
    Call<Dish> addDish(@Part("description")Dish dish,
                       @Part MultipartBody.Part photo
                       );

}
