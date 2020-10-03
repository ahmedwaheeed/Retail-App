package com.example.retailapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retailapp.Home.HomeModel;
import com.example.retailapp.R;
import com.example.retailapp.Room.RoomClass;
import com.example.retailapp.asyncTask.updateAsyncTask;

import java.util.ArrayList;

public class CartRVAdapter extends RecyclerView.Adapter<CartRVAdapter.MyViewHolder> {

    Context context;
    ArrayList<HomeModel> modelArrayList;

    public CartRVAdapter(Context context, ArrayList<HomeModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cart_card_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        HomeModel model = modelArrayList.get(position);

        holder.CartProductsTitle.setText(model.getTitle());
        holder.CartProductPrice.setText((int)model.getPrice() + " EGP");
        holder.CartProductsDesc.setText(model.getDescription());
        Glide.with(context).load(model.getImage()).into(holder.CartProductsImage);

        holder.counterText.setText(String.valueOf(model.getQuantity()) );

        holder.IncCounterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                modelArrayList.get(position).setQuantity(modelArrayList.get(position).getQuantity() + 1);

                new updateAsyncTask(RoomClass.getProductDatabase(context).getProductsDao()).execute(modelArrayList.get(position));

                holder.counterText.setText(modelArrayList.get(position).getQuantity()+"");
            }
        });

        holder.DecCounterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                modelArrayList.get(position).setQuantity(modelArrayList.get(position).getQuantity() - 1);

                new updateAsyncTask(RoomClass.getProductDatabase(context).getProductsDao()).execute(modelArrayList.get(position));

                holder.counterText.setText(modelArrayList.get(position).getQuantity()+"");

            }
        });

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView CartProductsImage;
        TextView CartProductsTitle;
        TextView CartProductsDesc;
        TextView CartProductPrice;

        TextView counterText;

        ImageButton IncCounterBtn;
        ImageButton DecCounterBtn;

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);

           CartProductsImage = itemView.findViewById(R.id.cart_image_iv);
           CartProductsTitle = itemView.findViewById(R.id.cart_title_tv);
           CartProductsDesc =  itemView.findViewById(R.id.cart_desc_tv);
           CartProductPrice = itemView.findViewById(R.id.cart_price_tv);
           counterText = itemView.findViewById(R.id.counter_tv);
           IncCounterBtn = itemView.findViewById(R.id.inc_cart_btn);
           DecCounterBtn = itemView.findViewById(R.id.dec_cart_btn);

       }
   }
}
