package com.example.retailapp.WebServices;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoriesResponse {

    @SerializedName("categories")
    ArrayList<CategoryModel> categoryModels;

    public ArrayList<CategoryModel> getCategoryModels() {
        return categoryModels;
    }

    public void setCategoryModels(ArrayList<CategoryModel> categoryModels) {
        this.categoryModels = categoryModels;
    }
}
