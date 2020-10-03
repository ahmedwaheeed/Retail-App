package com.example.retailapp.asyncTask;

import android.os.AsyncTask;

import com.example.retailapp.Home.HomeModel;
import com.example.retailapp.Room.ProductsDAO;

public class insertAsyncTask extends AsyncTask<HomeModel, Void, Void> {

    public ProductsDAO productsDAO;

    public insertAsyncTask(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    @Override
    protected Void doInBackground(HomeModel... homeModels) {
        productsDAO.addToProducts(homeModels[0]);
        return null;
    }
}
