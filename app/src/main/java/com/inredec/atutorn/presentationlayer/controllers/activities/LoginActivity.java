package com.inredec.atutorn.presentationlayer.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.inredec.atutorn.model.businesslayer.entities.Login;
import com.inredec.atutorn.model.businesslayer.entities.User;
import com.inredec.atutorn.model.servicelayer.UserClient;

import com.inredec.atutorn.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.cb_remember)
    CheckBox cbRemember;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_register)
    Button btRegister;




    private UserClient userClient;
    private static String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //Hide keyboard on initial focus
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((etUsername.getText().toString().isEmpty()) || (etPassword.getText().toString().isEmpty())) {
                    if (etUsername.getText().toString().isEmpty()) Toast.makeText(LoginActivity.this,
                            "Correo no puede ser nulo",
                            Toast.LENGTH_SHORT).show();
                    else Toast.makeText(LoginActivity.this,
                            "Contraseña no puede ser nulo",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LoginActivity.this,
                            "Campos correo/pass rellenados",
                            Toast.LENGTH_SHORT).show();

                    // login();
                    // Cambiar activity
                    String user = "";
                    user = user + etUsername.getText().toString();
                    String psw = "";
                    psw = psw + etPassword.getText().toString();
                    if(user.equals("agauser@hotmail.com")&& psw.equals("1234")){
                        Intent myIntent = new Intent(LoginActivity.this, StatisticsActivity.class);
                        startActivity(myIntent);
                    }

                    else {
                        Intent myIntent = new Intent(LoginActivity.this, MainMenuActivity.class);
                        //Bundle extras = myIntent.getExtras();
                        startActivity(myIntent);
                        ///extras.putString("etUsernameValue", etUsername.getText().toString().trim());
                        //extras.putString("etValue", etUsername.getText().toString().trim());
                        //todo start new Activity
                    }

                }
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LoginActivity.this, TestActivity.class);
                startActivity(myIntent);
            }
        });
    }
    private void login(){
        NetworkService();
        Login login = new Login("agauser", "Secret");
        Call<User> call = userClient.login(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    Toast.makeText(LoginActivity.this,
                            response.body().getToken(),
                            Toast.LENGTH_SHORT).show();
                    //save token
                    token = response.body().getToken();
                }
                else{
                    Toast.makeText(LoginActivity.this,
                            "login not correct :(",
                            Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this,
                        "error :(",
                        Toast.LENGTH_SHORT).show();
            }
        });
        call.request().url();
    }

    private void getSecret(){
        Call<ResponseBody> call = userClient.getSecret(token);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        Toast.makeText(LoginActivity.this,
                                response.body().string(),
                                Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this,
                            "token is not correct :(",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this,
                        "error :(",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void  NetworkService(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        Retrofit.Builder builder = new Retrofit.Builder()
                //.baseUrl("http://10.0.2.2:5000")
                .baseUrl("https://atutor.appspot.com/api/1.0/atapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build());
        Retrofit retrofit = builder.build();

        userClient = retrofit.create(UserClient.class);
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 7;
    }

}

