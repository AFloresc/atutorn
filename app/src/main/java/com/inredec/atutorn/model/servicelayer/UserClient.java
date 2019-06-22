package com.inredec.atutorn.model.servicelayer;

import com.inredec.atutorn.model.businesslayer.entities.Login;
import com.inredec.atutorn.model.businesslayer.entities.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserClient {

    @POST("api/auth/signin")
    Call<User> login(@Body Login login);

    @GET("secretInfo")
    Call<ResponseBody> getSecret(@Header("Authorization") String authToken);

}
