package com.inredec.atutorn.presentationlayer.controllers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.inredec.atutorn.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.tv_welcome)
    TextView tvWelcome;
    @BindView(R.id.pb_loadtime)
    ProgressBar pbLoadtime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        tvWelcome.startAnimation(myanim);
        ivLogo.startAnimation(myanim);
        pbLoadtime.startAnimation(myanim);

        pbLoadtime.setProgress(0);

        //startActivity(new Intent(MainActivity.this, LoginActivity.class));
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                pbLoadtime.setProgress(100);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }, 3000);

    }
}
