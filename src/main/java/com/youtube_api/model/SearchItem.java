package com.youtube_api.model;

public class SearchItem {
    private String url;
    private String title;
    private String creator;
    private String description;
    private String thumbnail;

    public SearchItem(String url, String title, String creator, String description, String thumbnail) {
        this.url = url;
        this.title = title;
        this.creator = creator;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


}
