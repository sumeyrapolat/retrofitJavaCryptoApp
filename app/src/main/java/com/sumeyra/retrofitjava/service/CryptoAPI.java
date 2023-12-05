package com.sumeyra.retrofitjava.service;

import com.sumeyra.retrofitjava.model.CryptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {
    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
    // GET, POST, UPDATE, DELETE
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    Call<List<CryptoModel>> getData();
    //cryptomodeller currency ve price içeren ikili bundan birsürü gelecek bir call yap ve tüm modellerin bir listesini oluştur
    //bu iki satır kod ile tüm structure yapı kurulmuş oluyor.


}
