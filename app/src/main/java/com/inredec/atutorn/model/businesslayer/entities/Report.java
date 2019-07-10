package com.inredec.atutorn.model.businesslayer.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Report {

    @SerializedName("courseID")
    private long lessonID;

    @SerializedName("courseTitle")
    private String courseName;

    @SerializedName("numberUsers")
    private int numberUsers;

    @SerializedName("lessonsRead")
    private int lessonsRead;

    @SerializedName("maxMark")
    private int maxMark;

    @SerializedName("minMark")
    private int minMark;

    @SerializedName("avgMark")
    private float avgMark;

    @SerializedName("mostSearched")
    private String[] mostSearched;

    @SerializedName("lessons")
    private Lesson[] lessons;

    public Report(long lessonID, String courseName, int numberUsers, int lessonsRead, int maxMark, int minMark, float avgMark, String[] mostSearched, Lesson[] lessons) {
        this.lessonID = lessonID;
        this.courseName = courseName;
        this.numberUsers = numberUsers;
        this.lessonsRead = lessonsRead;
        this.maxMark = maxMark;
        this.minMark = minMark;
        this.avgMark = avgMark;
        this.mostSearched = mostSearched;
        this.lessons = lessons;
    }

    public long getLessonID() {
        return lessonID;
    }

    public void setLessonID(long lessonID) {
        this.lessonID = lessonID;
    }

    @Override
    public String toString() {
        return "Report{" +
                "lessonID=" + lessonID +
                ", courseName='" + courseName + '\'' +
                ", numberUsers=" + numberUsers +
                ", lessonsRead=" + lessonsRead +
                ", maxMark=" + maxMark +
                ", minMark=" + minMark +
                ", avgMark=" + avgMark +
                ", mostSearched=" + Arrays.toString(mostSearched) +
                ", lessons=" + Arrays.toString(lessons) +
                '}';
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getNumberUsers() {
        return numberUsers;
    }

    public void setNumberUsers(int numberUsers) {
        this.numberUsers = numberUsers;
    }

    public int getLessonsRead() {
        return lessonsRead;
    }

    public void setLessonsRead(int lessonsRead) {
        this.lessonsRead = lessonsRead;
    }

    public int getMaxMark() {
        return maxMark;
    }

    public void setMaxMark(int maxMark) {
        this.maxMark = maxMark;
    }

    public int getMinMark() {
        return minMark;
    }

    public void setMinMark(int minMark) {
        this.minMark = minMark;
    }

    public float getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(float avgMark) {
        this.avgMark = avgMark;
    }

    public String[] getMostSearched() {
        return mostSearched;
    }

    public void setMostSearched(String[] mostSearched) {
        this.mostSearched = mostSearched;
    }

    public Lesson[] getLessons() {
        return lessons;
    }

    public void setLessons(Lesson[] lessons) {
        this.lessons = lessons;
    }
}
