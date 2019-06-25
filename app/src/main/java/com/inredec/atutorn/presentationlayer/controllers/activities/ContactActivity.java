package com.inredec.atutorn.presentationlayer.controllers.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.inredec.atutorn.R;
import com.inredec.atutorn.utilitieslayer.SimpleMail;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactActivity extends AppCompatActivity {

    public static final String CONTACT_EMAIL_ADRESS = "arutor.2019.app@gmail.com";

    @BindView(R.id.et_contact_subject)
    EditText et_contact_mail_subject;

    @BindView(R.id.et_contact_message)
    EditText et_contact_mail_message;

    @BindView(R.id.bt_contact_scr_send)
    Button bt_contact_mail_send;

    @BindView(R.id.et_contact_from)
    EditText et_contact_mail_from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactctivity);
        ButterKnife.bind(this);

        bt_contact_mail_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String subject = et_contact_mail_subject.getText().toString();
                    String message = et_contact_mail_message.getText().toString();
                    String from = et_contact_mail_from.getText().toString().trim();

                    if (from.isEmpty()){
                        Toast.makeText(ContactActivity.this, "Debes rellenar tu email en el campo Mi correo", Toast.LENGTH_LONG).show();
                    }else if(subject.isEmpty()){
                        Toast.makeText(ContactActivity.this, "Debes introducir un Asunto", Toast.LENGTH_LONG).show();
                    }else if(message.isEmpty()){
                        Toast.makeText(ContactActivity.this, "Debes introducir un Mensaje", Toast.LENGTH_LONG).show();
                    }else{
                        //Todos lso campos rellenados
                        sendMail(subject, message, from);
                    }
                } catch (Exception e) {
                    e.printStackTrace();}

            }
        });
    }

    private void sendMail(String subject, String message, String from){
        new SimpleMail().sendEmail(CONTACT_EMAIL_ADRESS, subject, message, from);
        Toast.makeText(ContactActivity.this, "Mensaje enviado", Toast.LENGTH_LONG).show();
        et_contact_mail_subject.setText(null);
        et_contact_mail_from.setText(null);
        et_contact_mail_subject.setText(null);
        et_contact_mail_message.setText(null);

        /*;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + CONTACT_EMAIL_ADRESS));
        //intent.putExtra(Intent.EXTRA_EMAIL, CONTACT_EMAIL_ADRESS);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(intent);
    */
    }

}
