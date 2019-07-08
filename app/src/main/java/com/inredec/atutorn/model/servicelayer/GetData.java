package com.inredec.atutorn.model.servicelayer;

import com.inredec.atutorn.model.businesslayer.entities.Concept;
import com.inredec.atutorn.model.businesslayer.entities.Lesson;
import com.inredec.atutorn.model.businesslayer.entities.Questionary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetData {

    //Specify the request type and pass the relative URL//
    //Wrap the response in a Call object with the type of the expected result//
    //Lessons
    @GET("/lessons/languages/es")
    Call<List<Lesson>> getAllLessons();

    //Concepts
    @GET("/concepts/language/es")
    Call<List<Concept>> getAllConcepts();

    //Questionary
    @GET("/lessons/{lessonid}/questionary")
    Call<Questionary> getQuestionary(@Path("lessonid") String lessonID);

    /*
    @GET("/data/2.5/{movie_id}/getDetails")
Call < T > getMovieDatils(@Path("movie_id") String movieID);
     */
}

