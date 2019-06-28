package com.inredec.atutorn.presentationlayer.controllers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.inredec.atutorn.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainMenuActivity extends AppCompatActivity {

    //@BindView(R.id.bt_lessons)
    //Button btLessons;

    @BindView(R.id.bt_help)
    Button btHelp;

    @BindView(R.id.bt_profile)
    Button btProfile;

    @BindView(R.id.bt_lessons)
    Button btLessons;

    @BindView(R.id.bt_concepts)
    Button btConcepts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);

        btHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainMenuActivity.this, HelpActivity.class);
                startActivity(myIntent);
            }
        });

        btProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainMenuActivity.this, ContactActivity.class);
                startActivity(myIntent);
            }
        });

        btLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainMenuActivity.this, LessonsActivity.class);
                startActivity(myIntent);
            }
        });

        btConcepts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainMenuActivity.this, ConceptsActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
