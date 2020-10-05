package com.example.retailapp.WebServices;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServices {

    @GET("products")
    Call<ProductsResponse> getProducts();

    @GET("categories")
    Call<CategoriesResponse> getCategories();
}
