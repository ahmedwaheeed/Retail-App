package com.example.retailapp.Home;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



@Entity(tableName = "products")
public class HomeModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;

    @ColumnInfo(name = "description")
    @SerializedName("name")
    private String description;

    @ColumnInfo(name = "price")
    @SerializedName("price")
    private double price;

    @ColumnInfo(name = "avatar")
    @SerializedName("avatar")
    private String image;

    @ColumnInfo(name = "long_description")
    @SerializedName("description")
    private String descLong;


    @ColumnInfo(name = "quantity")
    private int quantity;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public HomeModel(String title, String description, double price, String image, String descLong) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
        this.descLong = descLong;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescLong() {
        return descLong;
    }

    public void setDescLong(String descLong) {
        this.descLong = descLong;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
