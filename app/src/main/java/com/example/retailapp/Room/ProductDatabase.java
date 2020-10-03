package com.example.retailapp.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.retailapp.Home.HomeModel;

@Database(entities = {HomeModel.class}, version = 1, exportSchema = false)
public abstract class ProductDatabase extends RoomDatabase {

    public abstract ProductsDAO getProductsDao();

}
