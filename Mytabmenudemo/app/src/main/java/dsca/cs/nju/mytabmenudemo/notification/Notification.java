package dsca.cs.nju.mytabmenudemo.notification;

import java.io.Serializable;

/**
 * Created by manlei on 2017/4/22.
 */

public class Notification implements Serializable{
    private String title;
    private String content;

    public Notification(String title, String content){
        this.title = title;
        this.content = content;
    }
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }
}
