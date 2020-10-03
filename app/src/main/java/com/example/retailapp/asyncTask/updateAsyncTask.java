package com.example.retailapp.asyncTask;

import android.os.AsyncTask;

import com.example.retailapp.Home.HomeModel;
import com.example.retailapp.Room.ProductsDAO;

public class updateAsyncTask extends AsyncTask<HomeModel, Void,Void> {

    public ProductsDAO productsDAO;

    public updateAsyncTask(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    @Override
    protected Void doInBackground(HomeModel... homeModels) {

        productsDAO.updateProducts(homeModels[0]);

        return null;
    }
}
