package com.example.retailapp.asyncTask;

import android.os.AsyncTask;

import com.example.retailapp.Home.HomeModel;
import com.example.retailapp.Room.ProductsDAO;

import java.util.ArrayList;
import java.util.List;

public class getAsyncTask extends AsyncTask<Void, Void, List<HomeModel>> {

    public ProductsDAO productsDAO;

    public getAsyncTask(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    @Override
    protected List<HomeModel> doInBackground(Void... voids) {
        return productsDAO.getProducts();
    }
}
