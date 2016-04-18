package com.alwajabat.alwajabatdataentry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.alwajabat.alwajabatdataentry.R;
import com.alwajabat.alwajabatdataentry.model.CuisineModel;
import com.alwajabat.alwajabatdataentry.model.PrimaryModel;
import com.alwajabat.alwajabatdataentry.model.SecondaryModel;


public class ConfirmationActivity extends AppCompatActivity {

    private PrimaryModel primaryModel;
    private SecondaryModel secondaryModel;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        primaryModel = (PrimaryModel) getIntent().getSerializableExtra("primary_details");
        secondaryModel = (SecondaryModel) getIntent().getSerializableExtra("secondary_details");

        textView = (TextView) findViewById(R.id.text);
        textView.setText(""+primaryModel.getRestaurantName()+"\n"+getCuisines());

    }

    private String getCuisines(){
        StringBuilder stringBuilder = new StringBuilder();

        for(CuisineModel model : secondaryModel.getCuisines()){
            stringBuilder.append(model.getName()+"\t");
        }

        return stringBuilder.toString();

    }
}
