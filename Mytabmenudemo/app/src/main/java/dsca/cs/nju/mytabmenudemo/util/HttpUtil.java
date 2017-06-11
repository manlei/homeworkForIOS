package dsca.cs.nju.mytabmenudemo.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by manlei on 2017/4/12.
 */

public class HttpUtil {
    public static String res = null;

    public static void getOkHttp(String url,okhttp3.Callback callback){
        res = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void postOkHttp(String url, RequestBody requestBody,okhttp3.Callback callback){
        res = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
