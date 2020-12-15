package com.celik.cryptocurrency.model;

import com.google.gson.annotations.SerializedName;

public class Currency {

    @SerializedName("currency")
    private String name;

    @SerializedName("price")
    private String price;


    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
