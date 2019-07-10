package com.inredec.atutorn.presentationlayer.controllers.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.inredec.atutorn.R;
import com.inredec.atutorn.model.businesslayer.entities.User;
import com.inredec.atutorn.model.servicelayer.JsonPlaceHolderApi;
import com.inredec.atutorn.utilitieslayer.SimpleMail;

import java.math.BigDecimal;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class ContactActivity extends AppCompatActivity {

    private static final String CONTACT_EMAIL_ADRESS = "atutor.contact@gmail.com";
    private static final String CONTACT_PASS ="atutor2019_12345";
    private Session session;
    private static final String TAG = "CONTACTACTIVITY";

    @BindView(R.id.et_contact_subject)
    EditText et_contact_mail_subject;

    @BindView(R.id.et_contact_message)
    EditText et_contact_mail_message;

    @BindView(R.id.bt_contact_scr_send)
    Button bt_contact_mail_send;

    @BindView(R.id.tv_contact_scr_from)
    TextView tv_text;

    @BindView(R.id.tv_profile_avg)
    TextView tv_avgMark;

    @BindView(R.id.tv_profile_username)
    TextView tv_userName;

    @BindView(R.id.tv_profile_mail)
    TextView tv_userMail;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactctivity);
        ButterKnife.bind(this);

        tv_text.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://atutor.appspot.com/api/1.0/atapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<User> call = jsonPlaceHolderApi.getUser("1");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(ContactActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = response.body();

                Log.d(TAG, response.toString());
                Log.d(TAG, "Response body: "+ response.body().toString());

                loadDataList(response.body());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        /*

 List<Lesson> lessons = response.body();
         Call<List<Lesson>> call = jsonPlaceHolderApi.getLessons();

        call.enqueue(new Callback<List<Lesson>>() {
         */

        bt_contact_mail_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    String subject = et_contact_mail_subject.getText().toString();
                    String message = et_contact_mail_message.getText().toString();


                    if(subject.isEmpty() || (message.isEmpty())){
                        if(subject.isEmpty()) Toast.makeText(ContactActivity.this, "Debes introducir un Asunto", Toast.LENGTH_LONG).show();
                        else  Toast.makeText(ContactActivity.this, "Debes introducir un Mensaje", Toast.LENGTH_LONG).show();
                    }else {
                        //Todos lso campos rellenados
                        sendMail(subject, message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();}

            }
        });
    }

    private void loadDataList(User user) {
        tv_userName.setText("Nombre: " +  user.getName());
        tv_userMail.setText("Email: " +  user.getMail());
        if (user.getMarks().length==0){
            tv_avgMark.setText("Nota media: No hay notasregistradas");
        }else{
            tv_avgMark.setText("Nota media: " + calcAvg(user.getMarks()));
        }


    }

    private float calcAvg(int[] marks) {
        int size = marks.length;
        if (size == 0) return 0;
        float sum = 0;
        for (int i : marks){
            sum = sum + i;
        }
        Log.d(TAG, "SUMA NOTAS: "+ sum + " TAMAÑO: ");

        return round(sum/size, 2);
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        Log.d(TAG, "Sin redondear: "+ d + " Redondeado: " + bd.floatValue());
        return bd.floatValue();
    }

    private void sendMail(String subject, String message){
        String[] TO = {"atutor.contact@gmail.com"}; //Direcciones email  a enviar.


        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email."));
            Log.i("EMAIL", "Enviando email...");
        }
        catch (android.content.ActivityNotFoundException e) {
            Toast.makeText(this, "NO existe ningún cliente de email instalado!.", Toast.LENGTH_SHORT).show();
        }
        //new SimpleMail().sendEmail(CONTACT_EMAIL_ADRESS, subject, message, from);
        /*StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.googlemail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactrory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        try{

            session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return  new PasswordAuthentication(CONTACT_EMAIL_ADRESS, CONTACT_PASS);
                }
            });

            if (session!=null){
                javax.mail.Message message_S = new MimeMessage(session);
                message_S.setFrom(new InternetAddress(from));
                message_S.setSubject(subject);
                message_S.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse("marxxxx@gmail.com"));
                message_S.setContent(message,"text/html; charset=utf-8");
                Transport.send(message_S); }
        }catch (Exception e){
            e.printStackTrace();
        }*/




        Toast.makeText(ContactActivity.this, "Mensaje enviado", Toast.LENGTH_LONG).show();
        et_contact_mail_subject.setText(null);
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
