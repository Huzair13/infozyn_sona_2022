package com.example.infozyn_sona.feed;


public class sonaNews {
    private String postimage;
    private String titleiv;
    private String message;
    private String date;
    private String time;
    private String key;

    public boolean isShrink() {
        return isShrink;
    }

    public void setShrink(boolean shrink) {
        isShrink = shrink;
    }

    private boolean isShrink=true;

    public sonaNews(){
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public sonaNews(String postimage, String titleiv, String message, String date, String time, String key) {
        this.postimage = postimage;
        this.titleiv = titleiv;
        this.message = message;
        this.time=time;
        this.date=date;
        this.key = key;
    }

    public String getPostimage() {
        return postimage;
    }

    public void setPostimage(String postimage) {
        this.postimage = postimage;
    }

    public String getTitleiv() {
        return titleiv;
    }

    public void setTitleiv(String titleiv) {
        this.titleiv = titleiv;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}