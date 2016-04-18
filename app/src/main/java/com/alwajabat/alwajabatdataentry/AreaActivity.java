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
import com.alwajabat.alwajabatdataentry.model.AreaModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class AreaActivity extends AppCompatActivity {

    APIAdapter apiAdapter;
    EditText etArea;
    AllListAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Area");

        etArea = (EditText) findViewById(R.id.et_add_area);

        apiAdapter =  new APIAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.area_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadAreas();

    }


    public void addArea(View view){

        apiAdapter.addArea(etArea.getText().toString(), new APIResponseCallback() {
            @Override
            public void onSuccess(Response response) {
                Response<AreaModel> areaModelResponse = response;

                if(areaModelResponse.code() == 200){
                    Toast.makeText(AreaActivity.this, "Area Added",Toast.LENGTH_SHORT).show();
                    loadAreas();
                }

                else
                    try {

                        Toast.makeText(AreaActivity.this, areaModelResponse.errorBody().string(),Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

            @Override
            public void onFailure(String error) {
                Log.e("Res Err", error);
                Toast.makeText(AreaActivity.this, error,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadAreas(){


        apiAdapter.getAreas(new APIResponseCallback() {
            @Override
            public void onSuccess(Response response) {
                Response<List<AreaModel>> areas = response;
                List<String> titles =  new ArrayList<String>();
                for(AreaModel area : areas.body()){
                    Log.e("Area", area.getId() + "\t" + area.getName());
                    titles.add(area.getName());
                }

                adapter = new AllListAdapter(AreaActivity.this, titles);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();



            }

            @Override
            public void onFailure(String error) {
                Log.e("Res Err", error);
                Toast.makeText(AreaActivity.this, error,Toast.LENGTH_SHORT).show();
            }
        });
    }
    }
