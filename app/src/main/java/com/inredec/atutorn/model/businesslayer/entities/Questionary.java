package com.inredec.atutorn.model.businesslayer.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Questionary {

    @SerializedName("questionaryID")
    private long questionaryID;

    @SerializedName("description")
    private String description;

    @SerializedName("lessonID")
    private long lessonID;

    @SerializedName("questions")
    private Question[] questions;


    public Questionary(long questionaryID, String description, long lessonID, Question[] questions) {
        this.questionaryID = questionaryID;
        this.description = description;
        this.lessonID = lessonID;
        this.questions = questions;
    }

    public long getQuestionaryID() {
        return questionaryID;
    }

    public void setQuestionaryID(long questionaryID) {
        this.questionaryID = questionaryID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getLessonID() {
        return lessonID;
    }

    public void setLessonID(long lessonID) {
        this.lessonID = lessonID;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Questionary{" +
                "questionaryID=" + questionaryID +
                ", description='" + description + '\'' +
                ", lessonID=" + lessonID +
                ", questions=" + Arrays.toString(questions) +
                '}';
    }
}
