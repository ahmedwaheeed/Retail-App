package com.example.retailapp.WebServices;

import com.example.retailapp.Home.HomeModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductsResponse {

    @SerializedName("products")
    ArrayList<HomeModel> modelArrayList = new ArrayList<>();

    public ArrayList<HomeModel> getModelArrayList() {
        return modelArrayList;
    }

    public void setModelArrayList(ArrayList<HomeModel> modelArrayList) {
        this.modelArrayList = modelArrayList;
    }
}
