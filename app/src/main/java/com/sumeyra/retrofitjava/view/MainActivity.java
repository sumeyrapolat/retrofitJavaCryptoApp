package com.sumeyra.retrofitjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sumeyra.retrofitjava.R;
import com.sumeyra.retrofitjava.adapter.RecyclerViewAdapter;
import com.sumeyra.retrofitjava.model.CryptoModel;
import com.sumeyra.retrofitjava.service.CryptoAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

    //öncelikle indireceğimiz data için bir arraylist e ihtiyacımız var
    List<CryptoModel> cryptoModels;
    private  String BASE_URL= "https://raw.githubusercontent.com/";
    //son olarak retrofit kısmını oluşturmamız gerekiyor
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.recyclerView);

        //RetroFit && JSON
        Gson gson = new GsonBuilder().setLenient().create(); //gson oluşturuyoruz
        retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)) //Json istiyor
                .build();

        loadData();
    }

    private void loadData(){
        //indirme işlemi için servisimi oluşturdum
        CryptoAPI cryptoAPI= retrofit.create(CryptoAPI.class);
        //şimdi call methodu le verilerimi indireceğim
        Call<List<CryptoModel>> call = cryptoAPI.getData();
        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                // cevap gelirse napıyım
                if (response.isSuccessful() ){
                    List<CryptoModel>responseList= response.body() ;
                    cryptoModels=responseList;

                    //RecyclerView
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(cryptoModels);
                    recyclerView.setAdapter(recyclerViewAdapter);


                }
            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
            //hata olursa ne yapayım
                t.printStackTrace();
            }
        });
    }

}