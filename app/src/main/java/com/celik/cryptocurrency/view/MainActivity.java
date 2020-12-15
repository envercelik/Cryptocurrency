package com.celik.cryptocurrency.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.celik.cryptocurrency.R;
import com.celik.cryptocurrency.adapter.RecyclerViewAdapter;
import com.celik.cryptocurrency.model.CryptoCurrency;
import com.celik.cryptocurrency.service.CryptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    ArrayList<CryptoCurrency> currencies;
    String BASE_URL = "https://api.nomics.com/v1/";
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);


        //Retrofit & Json
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();


        loadData();


    }




    public void loadData(){
        CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);

        Call<List<CryptoCurrency>> call = cryptoAPI.getData();

        call.enqueue(new Callback<List<CryptoCurrency>>() {
            @Override
            public void onResponse(Call<List<CryptoCurrency>> call, Response<List<CryptoCurrency>> response) {
                if (response.isSuccessful()){
                    List<CryptoCurrency> responseList = response.body();
                    currencies = new ArrayList<>(responseList);

                    recyclerViewAdapter = new RecyclerViewAdapter(currencies);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<CryptoCurrency>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

