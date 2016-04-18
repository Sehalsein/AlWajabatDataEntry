package com.alwajabat.alwajabatdataentry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button newRestaurantButton;
    private Button cusineButton;
    private Button areaButton;
    private Button paymentButton;
    private Button restaurantTypeButton;
    private Button ammenityButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        initializeViews();
    }

    private void initializeViews(){

        newRestaurantButton = (Button) findViewById(R.id.button_new_restaurant);
        cusineButton = (Button) findViewById(R.id.button_cuisine);
        areaButton = (Button) findViewById(R.id.button_area);
        paymentButton = (Button) findViewById(R.id.button_payment_method);
        restaurantTypeButton = (Button) findViewById(R.id.button_restaurant_type);
        ammenityButton = (Button) findViewById(R.id.button_ammenity);
    }

    public void cuisine(View view){
        startActivity(new Intent(MainActivity.this, CuisineActivity.class));
    }

    public void area(View view){
        startActivity(new Intent(MainActivity.this, AreaActivity.class));
    }

}
