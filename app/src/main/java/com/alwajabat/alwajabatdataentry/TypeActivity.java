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
import com.alwajabat.alwajabatdataentry.model.TypeModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class TypeActivity extends AppCompatActivity {

    APIAdapter apiAdapter;
    EditText etType;
    AllListAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Restaurant Type");

        etType = (EditText) findViewById(R.id.et_add_type);

        apiAdapter =  new APIAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.types_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        loadTypes();


    }


    public void addType(View view){

        apiAdapter.addType(etType.getText().toString(), new APIResponseCallback() {
            @Override
            public void onSuccess(Response response) {
                Response<TypeModel> typeResponse = response;

                if(typeResponse.code() == 200){
                    Toast.makeText(TypeActivity.this, "Type Added",Toast.LENGTH_SHORT).show();

                    loadTypes();
                }

                else
                    try {

                        Toast.makeText(TypeActivity.this, typeResponse.errorBody().string(),Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

            @Override
            public void onFailure(String error) {
                Log.e("Res Err", error);
                Toast.makeText(TypeActivity.this, error,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadTypes(){

        apiAdapter.getTypes(new APIResponseCallback() {
            @Override
            public void onSuccess(Response response) {
                Response<List<TypeModel>> types = response;
                List<String> titles =  new ArrayList<String>();
                for(TypeModel type : types.body()){
                    Log.e("Type", type.getId() + "\t" + type.getName());
                    titles.add(type.getName());
                }

                adapter = new AllListAdapter(TypeActivity.this, titles);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();



            }

            @Override
            public void onFailure(String error) {
                Log.e("Res Err", error);
                Toast.makeText(TypeActivity.this, error,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
