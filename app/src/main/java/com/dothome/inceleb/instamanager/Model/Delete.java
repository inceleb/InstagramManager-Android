package com.dothome.inceleb.instamanager.Model;


public class Delete {
    public String id;
    public String message;
    public String comment;
    public String sumnail_pic;
    public String profile_pic;
    public String date;

    public Delete(String id,String message,String comment,String date,String sumnail_pic,String profile_pic){
        this.id=id;
        this.message=message;
        this.comment=comment;
        this.sumnail_pic=sumnail_pic;
        this.profile_pic=profile_pic;
        this.date=date;
    }
}
