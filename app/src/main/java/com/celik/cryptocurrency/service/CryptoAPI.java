package com.celik.cryptocurrency.service;

import com.celik.cryptocurrency.model.CryptoCurrency;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {

    @GET("prices?key=052f0bbcdc2a27ca7c7aea8ccb3d801b")
    Call<List<CryptoCurrency>> getData();
}
