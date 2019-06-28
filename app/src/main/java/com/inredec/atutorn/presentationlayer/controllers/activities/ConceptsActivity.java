package com.inredec.atutorn.presentationlayer.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.inredec.atutorn.R;
import com.inredec.atutorn.model.businesslayer.entities.Concept;
import com.inredec.atutorn.model.servicelayer.JsonPlaceHolderApi;
import com.inredec.atutorn.presentationlayer.adapters.MyConceptAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConceptsActivity extends AppCompatActivity implements MyConceptAdapter.OnConceptListener{

    private static final String TAG = "ConceptsActivity";

    private MyConceptAdapter myAdapter;
    private RecyclerView myRecyclerView;

    private List<Concept> concepts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concepts);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://atutor.appspot.com/api/1.0/atapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Concept>> call = jsonPlaceHolderApi.getConcepts();

        call.enqueue(new Callback<List<Concept>>(){

            @Override
            public void onResponse(Call<List<Concept>> call, Response<List<Concept>> response) {
                if (!response.isSuccessful()){
                    //textViewResult.setText("Code: " + response.code());
                    Toast.makeText(ConceptsActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Concept> concepts = response.body();

                Log.d("ONRESPONSE", response.toString());
                Log.d("ONRESPONSE", "Response body: "+ response.body().toString());
                for (Concept concept : concepts){
                    Log.d("ONRESPONSE", concept.toString());
                }
                loadDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Concept>> call, Throwable t) {

            }
        });
    }


    private void loadDataList(List<Concept> body) {
        myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyConceptAdapter(concepts, ConceptsActivity.this);

        this.concepts = concepts;
        //Set the Adapter to the RecyclerView//
        myRecyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onConceptClick(int position) {

    }
}