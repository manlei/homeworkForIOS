package dsca.cs.nju.mytabmenudemo.util;

import android.app.Application;
import android.content.Context;

import dsca.cs.nju.mytabmenudemo.model.User;

public class AppContext extends Application {

    private static Context context;

    private static User onlineUser;

    private static boolean onlineUserUpdated = false;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 保存登录用户
     *
     * @param u 登录的用户
     * @return 成功登录返回 true；登录冲突返回 false
     */
    public static boolean signIn(User u) {
        if (onlineUser != null) {
            return false;
        } else {
            onlineUser = u;
            return true;
        }
    }

    /**
     * 获得当前登陆的用户
     *
     * @return 当前登录的用户
     */
    public static User getOnlineUser() {
        return onlineUser;
    }


}