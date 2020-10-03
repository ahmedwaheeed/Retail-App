package com.example.retailapp.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.retailapp.Home.HomeModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ProductsDAO {


    @Insert
    void addToProducts(HomeModel model);

    @Query("SELECT * FROM products")
    List<HomeModel> getProducts();

    @Query("DELETE FROM products")
    void deleteAllFromDatabase();

    @Update
    void updateProducts(HomeModel model);

}
