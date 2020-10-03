package com.example.retailapp.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.retailapp.Home.HomeModel;
import com.example.retailapp.R;

public class ProductDetailsFragment extends Fragment {

    TextView title_tv;
    TextView disc_tv;
    TextView disc_long_tv;
    ImageView image_iv;
    Button addToCart_btn;
    TextView price_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_product_details, container, false);

        assert getArguments() != null;
        HomeModel model = (HomeModel) getArguments().getSerializable("model");

        title_tv = view.findViewById(R.id.details_title_tv);
        disc_tv = view.findViewById(R.id.details_dis_tv);
        disc_long_tv = view.findViewById(R.id.details_des_long_tv);
        price_tv = view.findViewById(R.id.details_price_tv);
        image_iv = view.findViewById(R.id.details_image_iv);

        assert model != null;
        title_tv.setText(model.getTitle());
        disc_tv.setText(model.getDescription());
        price_tv.setText(String.valueOf((int)model.getPrice()) + " EGP");
        disc_long_tv.setText(model.getDescLong());
        Glide.with(requireContext()).load(model.getImage()).into(image_iv);


        return view;
    }
}