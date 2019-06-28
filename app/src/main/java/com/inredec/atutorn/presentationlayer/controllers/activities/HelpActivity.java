package com.inredec.atutorn.presentationlayer.controllers.activities;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.inredec.atutorn.R;
import com.inredec.atutorn.model.businesslayer.entities.Lesson;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class HelpActivity extends AppCompatActivity {

    @BindView(R.id.tv_text2_lecciones)
    TextView tv_text2_lessons;

    @BindView(R.id.tv_text2_conceptos)
    TextView tv_text2_concepts;

    @BindView(R.id.tv_text2_opciones)
    TextView tv_text2_options;

    @BindView(R.id.tv_text2_profile)
    TextView tv_text2_profile;

    @BindView(R.id.tv_text1)
    TextView tv_main_title;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ButterKnife.bind(this);

        tv_text2_lessons.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        tv_text2_concepts.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        tv_text2_options.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        tv_text2_profile.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        tv_main_title.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);

        loaddata();
    }

    private void loaddata(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("clicked_lesson", null);
        Type type = new TypeToken<Lesson>() {}.getType();
        Lesson lesson = gson.fromJson(json, type);
        if (lesson == null){
            lesson = new Lesson(1010, "NEW CREATED", "NO LLEGAN LOS DATOS", "HTTP://NOIMAGE.COM","ES",null, 1012, 1);
        }
        Log.d("ONLOADDATA" ,"Lesson: "+ lesson.toString());
    }
}
