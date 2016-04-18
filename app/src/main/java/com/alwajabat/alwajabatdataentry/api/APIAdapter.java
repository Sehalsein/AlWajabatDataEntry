package com.alwajabat.alwajabatdataentry.api;

import com.alwajabat.alwajabatdataentry.App;
import com.alwajabat.alwajabatdataentry.api.callback.APIResponseCallback;
import com.alwajabat.alwajabatdataentry.model.AreaModel;
import com.alwajabat.alwajabatdataentry.model.CuisineModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by deezdroid on 18/04/16.
 */
public class APIAdapter {

    private Retrofit retrofit;
    private EndPoints endPoints;

    public APIAdapter() {

         retrofit = new Retrofit.Builder()
                .baseUrl(App.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        endPoints = retrofit.create(EndPoints.class);
    }

    public void addCuisine(final String cuisine, final APIResponseCallback callback){

        CuisineModel model =  new CuisineModel();
        model.setName(cuisine);

        Call<CuisineModel> call = endPoints.newCuisine(model);

        call.enqueue(new Callback<CuisineModel>() {
            @Override
            public void onResponse(Call<CuisineModel> call, Response<CuisineModel> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<CuisineModel> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });


    }


    public void getCuisine( final APIResponseCallback callback){


        Call<List<CuisineModel>> call = endPoints.getCuisines();

        call.enqueue(new Callback<List<CuisineModel>>() {
            @Override
            public void onResponse(Call<List<CuisineModel>> call, Response<List<CuisineModel>> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<List<CuisineModel>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void addArea(final String area, final APIResponseCallback callback){

        AreaModel model = new AreaModel();
        model.setName(area);

        Call<AreaModel> call = endPoints.newArea(model);

        call.enqueue(new Callback<AreaModel>() {
            @Override
            public void onResponse(Call<AreaModel> call, Response<AreaModel> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<AreaModel> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });

    }


    public void getAreas( final APIResponseCallback callback){


        Call<List<AreaModel>> call = endPoints.getAreas();

        call.enqueue(new Callback<List<AreaModel>>() {
            @Override
            public void onResponse(Call<List<AreaModel>> call, Response<List<AreaModel>> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<List<AreaModel>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
