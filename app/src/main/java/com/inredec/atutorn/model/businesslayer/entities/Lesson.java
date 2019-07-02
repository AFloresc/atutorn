package com.inredec.atutorn.model.businesslayer.entities;


import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Lesson {

    @SerializedName("lessonID")
    private long lessonID;

    @SerializedName("titlee")
    private String title;

    @SerializedName("text")
    private String text;

    @SerializedName("image")
    private String image;

    @SerializedName("language")
    private String language;

    @SerializedName("contents")
    private Content[] contents;

    @SerializedName("courseID")
    private long courseID;

    @SerializedName("position")
    private int position;

   //@SerializedName("questionary")
   // private Questionary questionary;

    public Lesson(long lessonID, String title, String text, String image, String language, Content[] contents, long courseID, int position) {
        this.lessonID = lessonID;
        this.title = title;
        this.text = text;
        this.image = image;
        this.language = language;
        this.contents = contents;
        this.courseID = courseID;
        this.position = position;

    }
/*
    public Lesson(long lessonID, String title, String text, String image, String language, Content[] contents, long courseID, int position, Questionary questionary) {
        this.lessonID = lessonID;
        this.title = title;
        this.text = text;
        this.image = image;
        this.language = language;
        this.contents = contents;
        this.courseID = courseID;
        this.position = position;
        this.questionary = questionary;

    }
*/

    public long getLessonID() {
        return lessonID;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }

    public String getLanguage() {
        return language;
    }

    public Content[] getContents() {
        return contents;
    }

    public long getCourseID() {
        return courseID;
    }

    public int getPosition() {
        return position;
    }

    public void setLessonID(long lessonID) {
        this.lessonID = lessonID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setContents(Content[] contents) {
        this.contents = contents;
    }

    public void setCourseID(long courseID) {
        this.courseID = courseID;
    }

    public void setPosition(int position) {
        this.position = position;
    }

/*
    public Questionary getQuestionary() {
        return questionary;
    }

    public void setQuestionary(Questionary questionary) {
        this.questionary = questionary;
    }*/

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonID=" + lessonID +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", image='" + image + '\'' +
                ", language='" + language + '\'' +
                ", contents=" + Arrays.toString(contents) +
                ", courseID=" + courseID +
                ", position=" + position +
               /* ", questionary=" + questionary +*/
                '}';
    }
}
