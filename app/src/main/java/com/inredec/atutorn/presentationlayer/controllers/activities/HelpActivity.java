package com.inredec.atutorn.presentationlayer.controllers.activities;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.inredec.atutorn.R;

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
    }
}
