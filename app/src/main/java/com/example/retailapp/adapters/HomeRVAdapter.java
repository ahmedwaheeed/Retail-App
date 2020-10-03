package com.example.retailapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retailapp.Home.HomeModel;
import com.example.retailapp.MainActivity;
import com.example.retailapp.R;
import com.example.retailapp.Room.RoomClass;
import com.example.retailapp.asyncTask.insertAsyncTask;

import java.util.List;

public class HomeRVAdapter extends RecyclerView.Adapter<HomeRVAdapter.MyViewHolder> {

    List<HomeModel> productsList;
    public OnProductDetailsListener onProductDetailsListener;

    Context context;


    public interface OnProductDetailsListener{
        void onProductClickListener(View v, int position);
    }

    public HomeRVAdapter(List<HomeModel> productsList, OnProductDetailsListener onProductDetailsListener, Context context) {
        this.productsList = productsList;
        this.onProductDetailsListener = onProductDetailsListener;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final HomeModel model = productsList.get(position);

        holder.title_tv.setText(model.getTitle());
        holder.description_tv.setText(model.getDescription());
        holder.price_tv.setText(String.valueOf((int)model.getPrice()) + " EGP");
        Glide.with(context).load(model.getImage()).into(holder.imageView_iv);

        holder.add_to_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model.setQuantity(1);

                new insertAsyncTask(RoomClass.getProductDatabase(context).getProductsDao()).execute(model);

                ((AppCompatActivity) context).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                ((AppCompatActivity) context).getSupportActionBar().setTitle("Shopping Cart");


                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_cartFragment);

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onProductDetailsListener.onProductClickListener(view,holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title_tv;
        TextView description_tv;
        TextView price_tv;
        ImageButton add_to_cart_btn;
        ImageView imageView_iv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title_tv = itemView.findViewById(R.id.title_tv);
            description_tv = itemView.findViewById(R.id.desc_tv);
            price_tv = itemView.findViewById(R.id.price_tv);
            add_to_cart_btn = itemView.findViewById(R.id.add_to_cart_btn);
            imageView_iv = itemView.findViewById(R.id.image_iv);
        }
    }

}
