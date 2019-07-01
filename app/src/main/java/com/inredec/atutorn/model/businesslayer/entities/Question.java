package com.inredec.atutorn.model.businesslayer.entities;

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("Id")
    private int id;

    @SerializedName("heather")
    private String heather;

    @SerializedName("answer1")
    private String answer1;

    @SerializedName("answer2")
    private String answer2;

    @SerializedName("answer3")
    private String answer3;

    @SerializedName("answer4")
    private String answer4;

    @SerializedName("right_answer")
    private int right_answer;

    @SerializedName("choice")
    private int choice;

    public Question(int id, String heather, String answer1, String answer2, String answer3, String answer4, int right_answer, int choice) {
        this.id = id;
        this.heather = heather;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.right_answer = right_answer;
        this.choice = choice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getheather() {
        return heather;
    }

    public void setQuestion(String heather) {
        this.heather = heather;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public int getRight_answer() {
        return choice;
    }

    public void setRight_answer(int choice) {
        this.choice = choice;
    }
}

