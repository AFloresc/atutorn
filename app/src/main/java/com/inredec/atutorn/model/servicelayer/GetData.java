package com.inredec.atutorn.model.servicelayer;

import com.inredec.atutorn.model.businesslayer.entities.Lesson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetData {

    //Specify the request type and pass the relative URL//

    @GET("/lessons/languages/es")

//Wrap the response in a Call object with the type of the expected result//

    Call<List<Lesson>> getAllLessons();
}

