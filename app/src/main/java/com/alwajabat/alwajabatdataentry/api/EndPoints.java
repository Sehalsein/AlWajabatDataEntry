package com.alwajabat.alwajabatdataentry.api;

import com.alwajabat.alwajabatdataentry.model.AreaModel;
import com.alwajabat.alwajabatdataentry.model.CuisineModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by deezdroid on 18/04/16.
 */
public interface EndPoints {

    @POST("cuisine")
    Call<CuisineModel> newCuisine(@Body CuisineModel model);

    @GET("cuisine")
    Call<List<CuisineModel>> getCuisines();


    @POST("area")
    Call<AreaModel> newArea(@Body AreaModel model);

    @GET("area")
    Call<List<AreaModel>> getAreas();





}
