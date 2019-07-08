package com.inredec.atutorn.presentationlayer.controllers.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Looper;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.inredec.atutorn.R;
import com.inredec.atutorn.model.businesslayer.entities.Lesson;
import com.inredec.atutorn.model.businesslayer.entities.Question;
import com.inredec.atutorn.model.businesslayer.entities.Questionary;
import com.inredec.atutorn.model.servicelayer.JsonPlaceHolderApi;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    @BindView(R.id.iv_exitquiz)
    ImageView exitbt;

    @BindView(R.id.ly_coord)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.quiz_goback)
    Button btGoback;

    private Random r = new Random();

    private Question mQuestion;

    private int myScore;

    private Questionary  questionary;

    private Lesson lesson;

    private int index;

    private static final String TAG = "QuizActivity";
    @Override
    public void onBackPressed() {
        Toast.makeText(QuizActivity.this, "No puedes ir hacia atrás, responde para seguir", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ButterKnife.bind(this);

        //score.setText("Nota: " + getScore());

        exitbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Exit Quiz");
                Snackbar snackbar = Snackbar.make(coordinatorLayout,"¿Quieres salir?",Toast.LENGTH_SHORT);
                View sbView = snackbar.getView();
                sbView.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.card_bg_color));
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(getApplicationContext(),"Undo action",Toast.LENGTH_SHORT).show();
                        SharedPreferences sp = getSharedPreferences("your_prefs", QuizActivity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("lastQuestionIndex", -1);
                        editor.commit();
                        Intent myIntent = new Intent(QuizActivity.this, MainMenuActivity.class);
                        startActivity(myIntent);
                    }
                });
                snackbar.setActionTextColor(Color.BLUE);
                View snackbarView = snackbar.getView();
                TextView snackbarText = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                snackbarText.setTextColor(Color.BLACK);
                snackbar.show();

            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://atutor.appspot.com/api/1.0/atapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

       this.lesson = loadLessonData();
      
       this.index = 0;

        Log.d(TAG, "LessonID: "+this.lesson.getLessonID());
        Log.d(TAG, "Index: "+this.index);

        // If no data loaded
       // if (index == -1) {

            Call<Questionary> call = jsonPlaceHolderApi.getQuestionary(String.valueOf(lesson.getLessonID()));

            call.enqueue(new Callback<Questionary>() {


                @Override
                public void onResponse(Call<Questionary> call, Response<Questionary> response) {
                    if (!response.isSuccessful()) {
                        //textViewResult.setText("Code: " + response.code());
                        score.setText("Code: " + response.code());
                        Toast.makeText(QuizActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Questionary quest = response.body();
                    Log.d(TAG, "QUIESTIONARY: " + quest);
                    for (Question question : quest.getQuestions()) {
                        Log.d(TAG, "QUIESTION: " + question.toString());
                    }

                    //Shuffle the questions array
                    shuffleArray(quest.getQuestions());

                    //Show 1st question
                    question.setTypeface(null, Typeface.BOLD);
                    question.setText(quest.getQuestions()[0].getdescription());
                    answer1.setText(quest.getQuestions()[0].getAnswer1());
                    answer2.setText(quest.getQuestions()[0].getAnswer2());
                    answer3.setText(quest.getQuestions()[0].getAnswer3());
                    answer4.setText(quest.getQuestions()[0].getAnswer4());

                    questionary = quest;
                    index = 0;

                    }

                @Override
                public void onFailure(Call<Questionary> call, Throwable t) {
                    Toast.makeText(QuizActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }



            });

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = 1;
                questionary.getQuestions()[index].setChoice(val);
                Log.d("onclick", "index: "+index);
                index = index+1;
                Log.d("onClick", "SCORE: "+getScore(questionary));
                showData(index, questionary);

            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = 2;
                checkResult(val);
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = 3;
                checkResult(val);
            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = 4;
                checkResult(val);
            }
        });
    }

    // Random shuffle of questions based on Fisher–Yates shuffle
    static void shuffleArray(Question[] questions) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = questions.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Question a = questions[index];
            questions[index] = questions[i];
            questions[i] = a;
        }
    }

    private void showData( int index, Questionary quest){
        if (index <=9) {
            question.setTypeface(null, Typeface.BOLD);
            question.setText(quest.getQuestions()[index].getdescription());
            answer1.setText(quest.getQuestions()[index].getAnswer1());
            answer2.setText(quest.getQuestions()[index].getAnswer2());
            answer3.setText(quest.getQuestions()[index].getAnswer3());
            answer4.setText(quest.getQuestions()[index].getAnswer4());
//            score.setText(getScore(quest));
        }
        else{
            question.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
            int mark = getScore(quest);
            if (mark < 5){
                // Paint it red
                question.setTextColor(ContextCompat.getColor(this, R.color.red_exam));
                question.setText("NOTA: "+ mark + "\nDeberías repasar la lección y repetir el cuestionario");
            }else{
                // Paint it green
                question.setTextColor(ContextCompat.getColor(this, R.color.green_exam));
                if (mark < 7){
                    question.setText("NOTA: "+ mark + "\n¡No está mal!");
                } else{
                    if (mark <9) {
                        question.setText("NOTA: " + mark + "\n¡Buena nota!");
                    } else{
                        question.setText("NOTA: "+ mark + "\n¡Esxcelente!");
                    }
                }
            }

            //question.setText("NOTA: "+ getScore(quest));
            score.setText("Resultado");
            answer1.setVisibility(View.GONE);
            answer2.setVisibility(View.GONE);
            answer3.setVisibility(View.GONE);
            answer4.setVisibility(View.GONE);
            btGoback.setVisibility(View.VISIBLE);

            btGoback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Create object mark
                    // Save it on sharedPrefs
                    // Start
                }
            });
        }
    }

    private void checkResult(int val){
        questionary.getQuestions()[index].setChoice(val);
        Log.d("onclick", "index: "+index);
        index = index+1;
        Log.d("onClick", "SCORE: "+getScore(questionary));
        showData(index, questionary);
    }

    private void saveQuestionaryData(Questionary questionary, int lastQuestionIndex) {
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(questionary);
        editor.putString("actual_questionary", json);
        editor.putInt("lastQuestionIndex", lastQuestionIndex);
        if (lastQuestionIndex == 9) editor.putBoolean("finished", true);
        editor.apply();
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
        if (lastQuestionIndex == 9) editor.putBoolean("finished", true);
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

    private Questionary loadQuestionaryData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("actual_questionary", null);
        Type type = new TypeToken<Questionary>() {}.getType();
        Questionary questionary = gson.fromJson(json, type);
        Log.d("ONLOADDATA" ,"Questionary: "+ questionary.toString());
        return questionary;
    }

    private int loadLastIndexQuestion(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);
        return(sharedPreferences.getInt("lastQuestionIndex", -1));
    }

    private boolean hasFinished(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);
        return(sharedPreferences.getBoolean("finished", false));
    }

    private int getScore(Questionary questionary) {
        int s = 0;
        if (questionary != null) {
            for (Question question : questionary.getQuestions()) {
                if (question.getChoice() == question.getRight_answer())
                    s = s + 1;
            }
        }
        return s;
    }
}
