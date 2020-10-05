package com.example.retailapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retailapp.R;
import com.example.retailapp.WebServices.CategoryModel;

import java.util.List;

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.MyViewHolder> {

    List<CategoryModel> categoryModelList;
    Context context;

    public CategoryRVAdapter(List<CategoryModel> categoryModelList, Context context) {
        this.categoryModelList = categoryModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.catergories_card_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CategoryModel categoryModel = categoryModelList.get(position);

        holder.category_tv.setText(categoryModel.getName());
        Glide.with(context).load(categoryModel.getImage()).into(holder.category_iv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView category_iv;
        TextView category_tv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            category_iv = itemView.findViewById(R.id.category_iv);
            category_tv = itemView.findViewById(R.id.category_tv);
        }
    }

}
