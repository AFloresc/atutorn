package com.inredec.atutorn.presentationlayer.controllers.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.inredec.atutorn.R;
import com.inredec.atutorn.model.businesslayer.entities.Content;
import com.inredec.atutorn.model.businesslayer.entities.Lesson;
import com.inredec.atutorn.presentationlayer.adapters.MyLessonDetailAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LessonDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_detail_lesson_title)
    TextView tvLessonTitle;

    @BindView(R.id.tv_detail_lesson_desc)
    TextView tvLessonDescription;


    private Lesson lesson;
    private RecyclerView myRecyclerView;
    private MyLessonDetailAdapter myAdapter;
    private List<Content> contents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);

        ButterKnife.bind(this);

        this.lesson = loaddata();
        tvLessonTitle.setText(this.lesson.getTitle());
        tvLessonDescription.setText(this.lesson.getText());
        /*Glide.with(this)
                .load(lesson.getImage())
                .centerCrop()
                .override(100, 100)
                .centerCrop()
                .into(ivLessonImg);
        loadDataList(lesson.getContents());*/

        loadDataList(this.lesson.getContents());
    }

    private void loadDataList(Content[] contents) {
        List<Content> cts = new ArrayList<>();

        for (Content content : contents) {
            cts.add(content);
        }

        myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyLessonDetailAdapter(cts, LessonDetailActivity.this);

        this.contents = cts;
        //Set the Adapter to the RecyclerView//
        myRecyclerView.setAdapter(myAdapter);
    }

    private Lesson loaddata(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("clicked_lesson", null);
        Type type = new TypeToken<Lesson>() {}.getType();
        Lesson lesson = gson.fromJson(json, type);
        if (lesson == null){
            lesson = new Lesson(1010, "NEW CREATED", "NO LLEGAN LOS DATOS", "HTTP://NOIMAGE.COM","ES",null, 1012, 1);
        }
        Log.d("ONLOADDATA" ,"Lesson: "+ lesson.toString());
        return lesson;
    }
}
