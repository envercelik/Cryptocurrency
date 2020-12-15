package com.celik.cryptocurrency.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.celik.cryptocurrency.R;
import com.celik.cryptocurrency.model.Currency;
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

    ArrayList<Currency> currencies;
    String BASE_URL = "https://api.nomics.com/v1/";
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Retrofit & Json
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();


        loadData();
    }




    public void loadData(){
        CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);

        Call<List<Currency>> call = cryptoAPI.getData();

        call.enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {
                if (response.isSuccessful()){
                    List<Currency> responseList = response.body();
                    currencies = new ArrayList<>(responseList);

                    for (Currency currency:currencies){
                        System.out.println(currency.getName() + "  " + currency.getPrice());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

