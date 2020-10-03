package com.example.retailapp.asyncTask;

import android.os.AsyncTask;

import com.example.retailapp.Room.ProductsDAO;

public class deleteAsyncTask extends AsyncTask<Void,Void,Void> {

    public ProductsDAO productsDAO;

    public deleteAsyncTask(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        productsDAO.deleteAllFromDatabase();

        return null;
    }
}
