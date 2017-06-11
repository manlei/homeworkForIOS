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
//
//    /**
//     * 注销用户
//     *
//     * @return 成功注销返回 true
//     */
//    public static boolean signOut() {
//        onlineUser = null;
//        return true;
//    }

    /**
     * 获得当前登陆的用户
     *
     * @return 当前登录的用户
     */
    public static User getOnlineUser() {
        return onlineUser;
    }

//    /**
//     * 重新获取用户信息
//     *
//     * @return 更新是否成功
//     */
//    public static boolean updateOnlineUser() {
//        UsersService usersService = ApiClient.getUsersService();
//        usersService.fetchOne(onlineUser.getId()).enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                switch (response.code()) {
//                    case 200: {
//                        User u = response.body();
//                        u.setPassword(onlineUser.getPassword());
//                        onlineUser = u;
//                        onlineUserUpdated = true;
//                    } break;
//
//                    default: {
//                        onlineUserUpdated = false;
//                    } break;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                onlineUserUpdated = false;
//            }
//        });
//        return true;
//    }
//
//    /**
//     * 查看用户信息是否更新
//     *
//     * @return 是否更新
//     */
//    public static boolean isOnlineUserUpdated() {
//        return onlineUserUpdated;
//    }
//
//    /**
//     * 清空更新信息
//     *
//     */
//    public static void clearOnlineUserUpdated() {
//        onlineUserUpdated = false;
//    }

}