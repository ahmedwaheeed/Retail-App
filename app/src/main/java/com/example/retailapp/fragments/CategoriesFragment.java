package com.example.retailapp.fragments;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.retailapp.Home.HomeFragment;
import com.example.retailapp.Home.HomeModel;
import com.example.retailapp.R;
import com.example.retailapp.WebServices.CategoriesResponse;
import com.example.retailapp.WebServices.CategoryModel;
import com.example.retailapp.WebServices.ProductsResponse;
import com.example.retailapp.WebServices.RetrofitClass;
import com.example.retailapp.WebServices.WebServices;
import com.example.retailapp.adapters.CategoryRVAdapter;
import com.example.retailapp.adapters.HomeRVAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoriesFragment extends Fragment {

    WebServices webServices;
    RecyclerView recyclerView;
    CategoryRVAdapter adapter;
    ArrayList<CategoryModel> models = new ArrayList<>();
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        recyclerView = view.findViewById(R.id.recycleview);

        progressBar = view.findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new CategoriesFragment.GridSpacingItemDecoration(2, 20, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        callAPI();

        adapter = new CategoryRVAdapter(models,requireContext());
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }

    public void callAPI() {

        webServices = RetrofitClass.getRetrofit().create(WebServices.class);

        Call<CategoriesResponse> getCategory = webServices.getCategories();

        getCategory.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                progressBar.setVisibility(View.GONE);
                models.clear();
                models.addAll(response.body().getCategoryModels());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Couldn't load data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}