package com.inredec.atutorn.presentationlayer.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.inredec.atutorn.R;
import com.inredec.atutorn.model.businesslayer.entities.Question;

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

    private Question q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ButterKnife.bind(this);

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
}
