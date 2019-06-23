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
    }
}
