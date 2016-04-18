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
import com.alwajabat.alwajabatdataentry.model.CuisineModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class CuisineActivity extends AppCompatActivity {

    APIAdapter apiAdapter;
    EditText etCuisine;
    AllListAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Cuisine");

        etCuisine = (EditText) findViewById(R.id.et_add_cuisine);

        apiAdapter =  new APIAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.cuisines_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);



        loadCuisines();


    }

    public void addCuisine(View view){

        apiAdapter.addCuisine(etCuisine.getText().toString(), new APIResponseCallback() {
            @Override
            public void onSuccess(Response response) {
                Response<CuisineModel> cuisineResponse = response;

                if(cuisineResponse.code() == 200){
                    Toast.makeText(CuisineActivity.this, "Cuisine Added",Toast.LENGTH_SHORT).show();
                    loadCuisines();
                }

                else
                    try {

                        Toast.makeText(CuisineActivity.this, cuisineResponse.errorBody().string(),Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

            @Override
            public void onFailure(String error) {
                Log.e("Res Err", error);
                Toast.makeText(CuisineActivity.this, error,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadCuisines(){

        apiAdapter.getCuisine(new APIResponseCallback() {
            @Override
            public void onSuccess(Response response) {
                Response<List<CuisineModel>> cuisines = response;
                List<String> titles =  new ArrayList<String>();
                for(CuisineModel cuisine : cuisines.body()){
                    Log.e("Cuisine", cuisine.getId() + "\t" + cuisine.getName());
                    titles.add(cuisine.getName());
                }

                adapter = new AllListAdapter(CuisineActivity.this, titles);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();



            }

            @Override
            public void onFailure(String error) {
                Log.e("Res Err", error);
                Toast.makeText(CuisineActivity.this, error,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
