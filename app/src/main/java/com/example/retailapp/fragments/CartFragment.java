package com.example.retailapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.retailapp.Home.HomeModel;
import com.example.retailapp.R;
import com.example.retailapp.Room.RoomClass;
import com.example.retailapp.adapters.CartRVAdapter;
import com.example.retailapp.asyncTask.deleteAsyncTask;
import com.example.retailapp.asyncTask.getAsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class CartFragment extends Fragment {

    RecyclerView cartRv;
    CartRVAdapter adapter;

    Button clearAllButton;
    Button checkOut;

    ArrayList<HomeModel> modelArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        cartRv = view.findViewById(R.id.cart_rv);
        clearAllButton = view.findViewById(R.id.clear_cart_btn);
        checkOut = view.findViewById(R.id.check_out_btn);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        cartRv.setLayoutManager(layoutManager);
        adapter = new CartRVAdapter(requireContext(),modelArrayList);
        cartRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        getAllProductsFromRoom();


        clearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllData();
            }
        });

    }


    public void getAllProductsFromRoom(){
        List<HomeModel> model = new ArrayList<>();
        try {
            model = new getAsyncTask(RoomClass.getProductDatabase(requireContext()).getProductsDao()).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        modelArrayList.addAll(model);

        adapter.notifyDataSetChanged();
    }

    public void clearAllData(){
        new deleteAsyncTask(RoomClass.getProductDatabase(requireContext()).getProductsDao()).execute();

        modelArrayList.clear();

        adapter.notifyDataSetChanged();
    }

}