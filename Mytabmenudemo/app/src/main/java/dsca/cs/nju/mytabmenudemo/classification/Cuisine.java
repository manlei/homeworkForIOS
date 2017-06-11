package dsca.cs.nju.mytabmenudemo.classification;

/**
 * Created by manlei on 2017/4/22.
 */

public class Cuisine {
    private String name;
    private int imageId;

    public Cuisine(String name, int imageId){
        this.imageId = imageId;
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}
