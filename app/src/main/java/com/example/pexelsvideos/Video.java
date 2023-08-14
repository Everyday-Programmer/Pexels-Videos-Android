package com.example.pexelsvideos;

public class Video {
    String id, quality, file_type, link, userName, userUrl;
    int width, height, duration;

    public Video() {

    }

    public int getDuration() {
        return duration;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public String getUserName() {
        return userName;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getQuality() {
        return quality;
    }

    public String getLink() {
        return link;
    }

    public String getFile_type() {
        return file_type;
    }

    public String getId() {
        return id;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public void setId(String id) {
        this.id = id;
    }
}