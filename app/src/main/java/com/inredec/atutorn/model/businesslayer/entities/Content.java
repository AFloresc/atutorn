package com.inredec.atutorn.model.businesslayer.entities;

import com.google.gson.annotations.SerializedName;

public class Content  {

    @SerializedName("contentID")
    private long contentID;

    @SerializedName("title")
    private  String title;

    @SerializedName("lessonID")
    private long lessonID;

    @SerializedName("content")
    private String description;

    @SerializedName("image")
    private  String image;

    @SerializedName("position")
    private int position;

    public Content(long contentID, String title, long lessonID, String description, String image, int position) {
        this.contentID = contentID;
        this.title = title;
        this.lessonID = lessonID;
        this.description = description;
        this.image = image;
        this.position = position;
    }

    public long getContentID() {
        return contentID;
    }

    public String getTitle() {
        return title;
    }

    public long getLessonID() {
        return lessonID;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Content{" +
                "contentID=" + contentID +
                ", title='" + title + '\'' +
                ", lessonID=" + lessonID +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", position=" + position +
                '}';
    }
}
