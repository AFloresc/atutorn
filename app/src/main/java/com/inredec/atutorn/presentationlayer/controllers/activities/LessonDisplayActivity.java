package com.inredec.atutorn.presentationlayer.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.inredec.atutorn.R;
import com.inredec.atutorn.model.businesslayer.entities.Lesson;
import com.inredec.atutorn.model.servicelayer.GetData;
import com.inredec.atutorn.model.servicelayer.RetrofitClient;
import com.inredec.atutorn.presentationlayer.adapters.MyLessonAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LessonDisplayActivity extends AppCompatActivity {

    private MyLessonAdapter myAdapter;
    private RecyclerView myRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_display);

        //Create a handler for the RetrofitInstance interface//

        GetData service = RetrofitClient.getRetrofitInstance().create(GetData.class);


        Call<List<Lesson>> call = service.getAllLessons();

//Execute the request asynchronously//
        call.enqueue(new Callback<List<Lesson>>() {
            @Override
            //Handle a successful response//
            public void onResponse(Call<List<Lesson>> call, Response<List<Lesson>> response) {
                loadDataList(response.body());
            }
            //Handle execution failures//

            @Override
            public void onFailure(Call<List<Lesson>> call, Throwable t) {
                //If the request fails, then display the following toast//
                Toast.makeText(LessonDisplayActivity.this, "Unable to load lessons", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Display the retrieved data as a list//
    private void loadDataList(List<Lesson> lessons) {
        //Get a reference to the RecyclerView//

        myRecyclerView = findViewById(R.id.myRecyclerView);
        myAdapter = new MyLessonAdapter(lessons);

        //Set the Adapter to the RecyclerView//

        myRecyclerView.setAdapter(myAdapter);
    }


}
