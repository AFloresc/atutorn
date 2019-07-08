package com.inredec.atutorn.model.servicelayer;

import com.inredec.atutorn.model.businesslayer.entities.Concept;
import com.inredec.atutorn.model.businesslayer.entities.Lesson;
import com.inredec.atutorn.model.businesslayer.entities.Mark;
import com.inredec.atutorn.model.businesslayer.entities.Questionary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("lessons/languages/es")
    Call<List<Lesson>> getLessons();

    @GET("concepts/languages/es")
    Call<List<Concept>> getConcepts();

    @GET("lessons/{lessonid}/questionary")
    Call<Questionary> getQuestionary(@Path("lessonid") String lessonID);

   @POST("user/mark")
    Call<Mark> createMark(@Body Mark mark0);
}
