package com.inredec.atutorn.presentationlayer.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.inredec.atutorn.R;
import com.inredec.atutorn.model.businesslayer.entities.Mark;
import com.inredec.atutorn.model.servicelayer.JsonPlaceHolderApi;

import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostMarkActivity extends AppCompatActivity {
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_mark);

        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://atutor.appspot.com/api/1.0/atapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        createMak();
    }

    private void createMak() {
        Mark mark = new Mark(1l, 1l, 8);

    }
}
