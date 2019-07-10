package com.inredec.atutorn.model.businesslayer.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;


public class User {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String mail;

    @SerializedName("password")
    private String password;

    @SerializedName("url_image")
    private String url_image;

    @SerializedName("marks")
    private int[] marks;


    private String token;



    public User(String name, String mail, String password, String url_image, int[] marks, String token) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.url_image = url_image;
        this.marks = marks;
        this.token = token;
    }

    public User(){
        this.name = "";
        this.mail = "";
        this.password = "";
        this.url_image= "";
    }

    public User(String name, String mail, String password) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.url_image = " ";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public int[] getMarks() {
        return marks;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", url_image='" + url_image + '\'' +
                ", marks=" + Arrays.toString(marks) +
                ", token='" + token + '\'' +
                '}';
    }

}
