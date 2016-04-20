package com.alwajabat.alwajabatdataentry.api;

import com.alwajabat.alwajabatdataentry.model.AmmenitityModel;
import com.alwajabat.alwajabatdataentry.model.AreaModel;
import com.alwajabat.alwajabatdataentry.model.CuisineModel;
import com.alwajabat.alwajabatdataentry.model.PaymentModel;
import com.alwajabat.alwajabatdataentry.model.TypeModel;

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

    @POST("amenity")
    Call<AmmenitityModel> newAmenity(@Body AmmenitityModel model);

    @GET("amenity")
    Call<List<AmmenitityModel>> getAmenities();

    @POST("restauranttype")
    Call<TypeModel> newType(@Body TypeModel model);

    @GET("restauranttype")
    Call<List<TypeModel>> getTypes();

    @POST("payment")
    Call<PaymentModel> newPaymentType(@Body PaymentModel model);

    @GET("payment")
    Call<List<PaymentModel>> getPaymentTypes();



}
