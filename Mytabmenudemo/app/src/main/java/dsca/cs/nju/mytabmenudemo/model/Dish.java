package dsca.cs.nju.mytabmenudemo.model;

import java.io.Serializable;

/**
 * Created by manlei on 2017/6/2.
 * 说明：
 */

public class Dish implements Serializable {
    private String author;
    private String password;
    private String label;
    private String title;
    private String description;
    private String imageUrl;

    public Dish() {

    }

    public Dish(String author,String password,String label,String title,String description,String imageUrl) {
        this.author = author;
        this.description = description;
        this.password = password;
        this.label = label;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
