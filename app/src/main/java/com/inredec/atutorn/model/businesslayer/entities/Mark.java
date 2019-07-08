package com.inredec.atutorn.model.businesslayer.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Mark {

    @SerializedName("userID")
    private long userID;

    @SerializedName("questionaryID")
    private long questionaryID;

    @SerializedName("val")
    private int value;

    public Mark(long userID, long questionaryID, int value) {
        this.userID = userID;
        this.questionaryID = questionaryID;
        this.value = value;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getQuestionaryID() {
        return questionaryID;
    }

    public void setQuestionaryID(long questionaryID) {
        this.questionaryID = questionaryID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "userID=" + userID +
                ", questionaryID=" + questionaryID +
                ", value=" + value +
                '}';
    }
}
