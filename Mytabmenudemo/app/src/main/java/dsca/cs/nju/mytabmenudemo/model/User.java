package dsca.cs.nju.mytabmenudemo.model;

import java.io.Serializable;

/**
 * Created by manlei on 2017/5/31.
 * 说明：
 */

public class User implements Serializable{
    private String account;
    private boolean login;
    private String name;
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
