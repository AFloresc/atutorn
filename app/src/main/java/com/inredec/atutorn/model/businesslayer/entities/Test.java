package com.inredec.atutorn.model.businesslayer.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Test {

    @SerializedName("Id")
    private Long id;

    @SerializedName("description")
    private String title;

    @SerializedName("level")
    private String level;

    private ArrayList<Question> questions;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", level='" + level + '\'' +
                ", questions=" + questions +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
