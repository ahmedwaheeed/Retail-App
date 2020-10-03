package com.example.retailapp.Room;

import android.content.Context;

import androidx.room.Room;

public class RoomClass {

    public static ProductDatabase productDatabase;

    public static ProductDatabase getProductDatabase(Context context){

        if (productDatabase == null){

            productDatabase = Room.databaseBuilder(context,ProductDatabase.class,"retail_app")
                    .build();

        }

        return productDatabase;
    }
}
