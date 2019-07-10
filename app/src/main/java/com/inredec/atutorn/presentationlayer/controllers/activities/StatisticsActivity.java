package com.inredec.atutorn.presentationlayer.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.inredec.atutorn.R;
import com.inredec.atutorn.model.businesslayer.entities.Lesson;
import com.inredec.atutorn.model.businesslayer.entities.Report;
import com.inredec.atutorn.model.servicelayer.JsonPlaceHolderApi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatisticsActivity extends AppCompatActivity {


    @BindView(R.id.tv_report_title)
    TextView reportTitle;

    @BindView(R.id.tv_report_courseavgmark)
    TextView reportAvgMark;

    @BindView(R.id.tv_report_courselessonsread)
    TextView reportLessonsRead;

    @BindView(R.id.tv_report_coursemaxmark)
    TextView maxMark;

    @BindView(R.id.tv_report_courseminmark)
    TextView minMark;

    @BindView(R.id.tv_report_coursesearchterms)
    TextView searchTerms;

    @BindView(R.id.tv_report_coursetoplessons)
    TextView courseTopLessonsk;

    @BindView(R.id.tv_report_courseusers)
    TextView courseUsers;

    @BindView(R.id.tv_report_coursesearchetitle)
    TextView courseSearchTitle;

    @BindView(R.id.tv_report_coursetitle)
    TextView courseTitle;

    @BindView(R.id.tv_report_coursetoplessonstitle)
    TextView courseTopLessonsTitle;

    @BindView(R.id.bt_report_exit)
    Button bt_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ButterKnife.bind(this);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://atutor.appspot.com/api/1.0/atapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Report> call = jsonPlaceHolderApi.getReport("1");
        call.enqueue(new Callback<Report>() {
            @Override
            public void onResponse(Call<Report> call, Response<Report> response) {
                if (!response.isSuccessful()){
                    //textViewResult.setText("Code: " + response.code());
                    Toast.makeText(StatisticsActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Report report = response.body();


                loadData(report);


                bt_exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                        System.exit(0);
                    }
                });

            }

            @Override
            public void onFailure(Call<Report> call, Throwable t) {

            }
        });
    }

    private void loadData(Report report) {
        reportAvgMark.setText("Nota media: " + String.valueOf(report.getAvgMark()));
        reportLessonsRead.setText("Lecciones leidas: "+String.valueOf(report.getLessonsRead()));
        maxMark.setText("Nota máxima: "+ String.valueOf(report.getMaxMark()));
        minMark.setText("Nota mínima: "+ String.valueOf(report.getMinMark()));

        String search = "";
        for (String string : report.getMostSearched()){
            search = search + string + "\n";
        }
        searchTerms.setText(search);

        String lsns = "";
        for (Lesson lesson : report.getLessons()) {
            lsns = lsns + lesson.getTitle()+ "\n";
        }
        courseTopLessonsk.setText(lsns);
        courseUsers.setText("Usuarios registrados " + String.valueOf(report.getNumberUsers()));
        courseSearchTitle.setText("Búsquedas registradas");
        courseTitle.setText("Curso Android - 2019");
        courseTopLessonsTitle.setText("Lecciones más visitadas");
    }
}
