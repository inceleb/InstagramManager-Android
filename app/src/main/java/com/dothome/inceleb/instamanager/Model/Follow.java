package com.dothome.inceleb.instamanager.Model;

/**
 * Created by jje on 2017. 2. 9..
 */

public class Follow {
    public String id;
    public String name;
    public boolean isFollowMe   = false;
    public boolean isFollow     = false;
    public boolean isFavorite   = false;
    public String pic;
    public int likes            = 0;
    public int comments         = 0;
    public Follow(String id,String name,String pic){
        this.id=id;
        this.name=name;
        this.pic=pic;
    }
}
