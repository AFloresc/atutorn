package com.inredec.atutorn.presentationlayer.controllers.activities;

import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.inredec.atutorn.R;
import com.inredec.atutorn.model.businesslayer.entities.Lesson;
import com.inredec.atutorn.model.businesslayer.entities.Question;

import java.lang.reflect.Type;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizActivity extends AppCompatActivity {

    @BindView(R.id.score)
    TextView score;

    @BindView(R.id.question)
    TextView question;

    @BindView(R.id.answer1)
    Button answer1;

    @BindView(R.id.answer2)
    Button answer2;

    @BindView(R.id.answer3)
    Button answer3;

    @BindView(R.id.answer4)
    Button answer4;

    private Random r = new Random();

    private Question mQuestion;

    private int myScore;

    private Lesson lesson;

    @Override
    public void onBackPressed() {
        Toast.makeText(QuizActivity.this, "No puedes ir hacia atrás, responde para seguir", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ButterKnife.bind(this);

        score.setText("Nota: " + getScore());

        this.lesson = loadLessonData();
        int index = loadLastIndexQuestion();

        if (!finishedQuiz()){
            //seguir preguntando
            //return next index
            // mostrar valores de la question en el index
            //question.setText(lesson.getQuestionary().getQuestions()[index].getheather());
            //answer1.setText(lesson.getQuestionary().getQuestions()[index].getAnswer1());
            //answer2.setText(lesson.getQuestionary().getQuestions()[index].getAnswer2());
            //answer3.setText(lesson.getQuestionary().getQuestions()[index].getAnswer3());
            //answer4.setText(lesson.getQuestionary().getQuestions()[index].getAnswer4());

        }else{
            question.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            int mark = getScore();
            if (mark < 5){
                // Paint it red
                question.setTextColor(ContextCompat.getColor(this, R.color.content_text_color));
                question.setText("NOTA: "+ mark + " Deberías repasar la lección y repetir el cuestionario");
            }else{
                // Paint it green
                question.setTextColor(ContextCompat.getColor(this, R.color.content_text_color));
                if (mark < 7){
                    question.setText("NOTA: "+ mark + " ¡No esta mal!");
                } else{
                    if (mark <9) {
                        question.setText("NOTA: " + mark + " ¡Buena nota!");
                    } else{
                        question.setText("NOTA: "+ mark + " ¡Esxcelente!");
                    }
                }
            }

            question.setText("NOTA: "+ getScore());
            //save final score and show it on screen, as question but bigger
            //Hide all buttons but last one to go back to main menu
            // And remove all the sharedPreferences values.

        }


        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    //All the questions have answer
    private boolean finishedQuiz() {
        /*for (Question question : this.lesson.getQuestionary().getQuestions()){
            if(question.getChoice()==0){
                return false;
            }
        }*/
        return true;
    }

    private void saveData(Lesson lesson, int lastQuestionIndex){
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(lesson);
        editor.putString("clicked_lesson", json);
        editor.putInt("lastQuestionIndex", lastQuestionIndex);
        editor.apply();
    }

    private Lesson loadLessonData(){
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

    private int loadLastIndexQuestion(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);
        return(sharedPreferences.getInt("lastQuestionIndex", -1));
    }

    private int getScore() {
        int s = 0;
        /*for (Question question : this.lesson.getQuestions()) {
            if (question.getChoice() == question.getRight_answer())
                s = s + 1;
        }*/
        return s;
    }
}
