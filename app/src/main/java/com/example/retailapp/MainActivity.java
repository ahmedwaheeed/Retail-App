package com.example.retailapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        NavController controller = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        NavigationUI.setupWithNavController(bottomNavigationView, controller);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                if(toolbar.getTitle().equals("Shopping Cart")){

                    Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.action_cartFragment_to_homeFragment);

                }else {
                    Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.action_productDetailsFragment_to_homeFragment);
                }
                return true;
            case R.id.cart_menu:

                Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_cartFragment);
                toolbar.setTitle("Shopping Cart");
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
}