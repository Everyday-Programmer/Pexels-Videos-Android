package com.example.pexelsvideo;

public class Video {
    String id, quality, file_type, link, userName, userUrl;
    int width, height, duration;

    public Video() {

    }

    public String getId() {
        return id;
    }

    public String getFile_type() {
        return file_type;
    }


    public String getLink() {
        return link;
    }

    public String getQuality() {
        return quality;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}