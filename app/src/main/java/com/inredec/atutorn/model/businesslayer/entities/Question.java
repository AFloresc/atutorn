package com.inredec.atutorn.model.businesslayer.entities;

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("questionID")
    private int questionID;

    @SerializedName("description")
    private String description;

    @SerializedName("answer1")
    private String answer1;

    @SerializedName("answer2")
    private String answer2;

    @SerializedName("answer3")
    private String answer3;

    @SerializedName("answer4")
    private String answer4;

    @SerializedName("goodanswer")
    private int right_answer;

    @SerializedName("choice")
    private int choice;


    public Question(int questionID, String description, String answer1, String answer2, String answer3, String answer4, int right_answer, int choice) {
        this.questionID = questionID;
        this.description = description;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.right_answer = right_answer;
        this.choice = choice;
    }

    public int getId() {
        return questionID;
    }

    public void setId(int questionID) {
        this.questionID = questionID;
    }

    public String getdescription() {
        return description;
    }

    public String getAnswer4() {
        return answer4;
    }

    public int getChoice() {
        return choice;
    }

    public void setQuestion(String description) {
        this.description = description;
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
        return right_answer;
    }

    public void setRight_answer(int choice) {
        this.choice = choice;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + questionID +
                ", description='" + description + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", answer4='" + answer4 + '\'' +
                ", right_answer=" + right_answer +
                ", choice=" + choice +
                '}';
    }
}

