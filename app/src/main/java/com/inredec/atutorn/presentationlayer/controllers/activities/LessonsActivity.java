package com.inredec.atutorn.presentationlayer.controllers.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.inredec.atutorn.R;
import com.inredec.atutorn.model.businesslayer.entities.Concept;
import com.inredec.atutorn.model.businesslayer.entities.Lesson;
import com.inredec.atutorn.model.servicelayer.JsonPlaceHolderApi;
import com.inredec.atutorn.presentationlayer.adapters.MyLessonAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LessonsActivity extends AppCompatActivity implements MyLessonAdapter.OnLessonListener{
    private static final String TAG = "LessonsActivity";
    // private TextView textViewResult;
    private MyLessonAdapter myAdapter;
    private RecyclerView myRecyclerView;

    private List<Lesson> lessons;

    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        etSearch=(EditText)findViewById(R.id.et_lesson_seach);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        // textViewResult = findViewById(R.id.tv_view__result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://atutor.appspot.com/api/1.0/atapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Lesson>> call = jsonPlaceHolderApi.getLessons();

        call.enqueue(new Callback<List<Lesson>>() {
            @Override
            public void onResponse(Call<List<Lesson>> call, Response<List<Lesson>> response) {

                if (!response.isSuccessful()){
                    //textViewResult.setText("Code: " + response.code());
                    Toast.makeText(LessonsActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Lesson> lessons = response.body();
/*
                for (Lesson lesson : lessons){
                    String content = "";
                    content += "ID: " + lesson.getCourseID() + "\n";
                    content += "Title: " + lesson.getTitle() + "\n";
                    content += "Text: " + lesson.getText() + "\n";
                    content +=  "Image: " + lesson.getImage() + "\n\n";

                    textViewResult.append(content);
                }*/
                Log.d("ONRESPONSE", response.toString());
                Log.d("ONRESPONSE", "Response body: "+ response.body().toString());
                for (Lesson lesson : lessons){
                    Log.d("ONRESPONSE", lesson.toString());
                }
                loadDataList(response.body());

            }

            @Override
            public void onFailure(Call<List<Lesson>> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
                Toast.makeText(LessonsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());


            }
        });
    }

    //Filter RecyclerView by search
    private void filter(String text) {
        List<Lesson> filteredList = new ArrayList<>();

        for (Lesson lesson : lessons){
            if(lesson.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(lesson);
            }
        }

        myAdapter.filterList((ArrayList)filteredList);
    }

    //Display the retrieved data as a list//
    private void loadDataList(List<Lesson> lessons) {
        //Get a reference to the RecyclerView//
        // Log.d("ONRESPONSE", "loadDataList is being executed");
        // Log.d("ONRESPONSE",   "Lessons size:"+lessons.size());
        myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyLessonAdapter(lessons, LessonsActivity.this);

        this.lessons = lessons;
        //Set the Adapter to the RecyclerView//
        myRecyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onLessonClick(int position) {
        Log.d(TAG, "onNoteClick: clicked. Item position: " + position);
        Intent intent = new Intent(this, LessonDetailActivity.class);
        //look for parceable objects!!
        intent.putExtra("some_object", lessons.get(position).getLessonID());
        if (lessons.get(position).getContents()==null){
            Toast.makeText(LessonsActivity.this, "La lección no tiene contenido", Toast.LENGTH_SHORT).show();
        }
        else{
            saveData(lessons.get(position));
            startActivity(intent);
        }
    }

    private void saveData(Lesson lesson){

        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(lesson);
        editor.putString("clicked_lesson", json);
        editor.apply();
    }
}
