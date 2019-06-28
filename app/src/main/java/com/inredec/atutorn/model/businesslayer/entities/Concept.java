package com.inredec.atutorn.model.businesslayer.entities;

import com.google.gson.annotations.SerializedName;

public class Concept {

    @SerializedName("lessonID")
    private long conceptID;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private String language;

    @SerializedName("courseID")
    private long courseID;

    public Concept(long conceptID, String title, String description, String language, long courseID) {
        this.conceptID = conceptID;
        this.title = title;
        this.description = description;
        this.language = language;
        this.courseID = courseID;
    }

    public long getConceptID() {
        return conceptID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public long getCourseID() {
        return courseID;
    }

    @Override
    public String toString() {
        return "Concept{" +
                "conceptID=" + conceptID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", courseID=" + courseID +
                '}';
    }
}
