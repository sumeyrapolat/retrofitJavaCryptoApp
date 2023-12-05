package com.sumeyra.retrofitjava.model;

import com.google.gson.annotations.SerializedName;

public class CryptoModel {
    @SerializedName("currency") // burada datasetimdeki değeri referans veriyorum
    public String currency;

    @SerializedName("price")
    public String price;
}
