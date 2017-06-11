package dsca.cs.nju.mytabmenudemo.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by manlei on 2017/5/31.
 * 说明：
 */

public class ApiClient {

    private static final String BASE_URL = "http://114.212.134.156:3000/";

    private static final String BASE_image_URL = BASE_URL+"image/?parameter=";

    private static Retrofit client;

    private static UserService userService;

    private static Retrofit getClient() {
        if (client == null) {
            client = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return client;
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = getClient().create(UserService.class);
        }
        return userService;
    }

    public static String getBaseImageUrl(String url) {
        return BASE_image_URL+url;
    }

}