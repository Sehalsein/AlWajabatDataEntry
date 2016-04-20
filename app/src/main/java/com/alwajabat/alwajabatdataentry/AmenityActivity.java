package com.alwajabat.alwajabatdataentry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alwajabat.alwajabatdataentry.api.APIAdapter;
import com.alwajabat.alwajabatdataentry.api.callback.APIResponseCallback;
import com.alwajabat.alwajabatdataentry.model.AmmenitityModel;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class AmenityActivity extends AppCompatActivity {

    APIAdapter apiAdapter;
    EditText etAmenity;
    AllListAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amenity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Amenity");

        etAmenity = (EditText) findViewById(R.id.et_add_amenity);

        apiAdapter =  new APIAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.amenity_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadAmenities();

    }


    public void addAmenity(View view){

        apiAdapter.addAmenity(etAmenity.getText().toString(), new APIResponseCallback() {
            @Override
            public void onSuccess(Response response) {
                Response<AmmenitityModel> amenityResponse = response;

                if(amenityResponse.code() == 200){
                    Toast.makeText(AmenityActivity.this, "Amenity Added",Toast.LENGTH_SHORT).show();
                    loadAmenities();
                }

                else
                    try {

                        Toast.makeText(AmenityActivity.this, amenityResponse.errorBody().string(),Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

            @Override
            public void onFailure(String error) {
                Log.e("Res Err", error);
                Toast.makeText(AmenityActivity.this, error,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadAmenities(){

        apiAdapter.getAmenities(new APIResponseCallback() {
            @Override
            public void onSuccess(Response response) {
                Response<List<AmmenitityModel>> amenities = response;
                List<String> titles =  new ArrayList<String>();
                for(AmmenitityModel amenity : amenities.body()){
                    Log.e("Amenity", amenity.getId() + "\t" + amenity.getName());
                    titles.add(amenity.getName());
                }

                adapter = new AllListAdapter(AmenityActivity.this, titles);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();



            }

            @Override
            public void onFailure(String error) {
                Log.e("Res Err", error);
                Toast.makeText(AmenityActivity.this, error,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
