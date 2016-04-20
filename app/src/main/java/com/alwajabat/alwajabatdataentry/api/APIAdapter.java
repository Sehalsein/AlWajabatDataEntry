package com.alwajabat.alwajabatdataentry.api;

import com.alwajabat.alwajabatdataentry.App;
import com.alwajabat.alwajabatdataentry.api.callback.APIResponseCallback;
import com.alwajabat.alwajabatdataentry.model.AmmenitityModel;
import com.alwajabat.alwajabatdataentry.model.AreaModel;
import com.alwajabat.alwajabatdataentry.model.CuisineModel;
import com.alwajabat.alwajabatdataentry.model.PaymentModel;
import com.alwajabat.alwajabatdataentry.model.TypeModel;

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

    public void addType(final String type, final APIResponseCallback callback){


        TypeModel model = new TypeModel();
        model.setName(type);

        Call<TypeModel> call = endPoints.newType(model);

        call.enqueue(new Callback<TypeModel>() {
            @Override
            public void onResponse(Call<TypeModel> call, Response<TypeModel> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<TypeModel> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });

    }


    public void getTypes( final APIResponseCallback callback){


        Call<List<TypeModel>> call = endPoints.getTypes();

        call.enqueue(new Callback<List<TypeModel>>() {
            @Override
            public void onResponse(Call<List<TypeModel>> call, Response<List<TypeModel>> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<List<TypeModel>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }


    public void addAmenity(final String amenity, final APIResponseCallback callback){



        AmmenitityModel model =  new AmmenitityModel();
        model.setName(amenity);

        Call<AmmenitityModel> call = endPoints.newAmenity(model);

        call.enqueue(new Callback<AmmenitityModel>() {
            @Override
            public void onResponse(Call<AmmenitityModel> call, Response<AmmenitityModel> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<AmmenitityModel> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });

    }


    public void getAmenities( final APIResponseCallback callback){


        Call<List<AmmenitityModel>> call = endPoints.getAmenities();

        call.enqueue(new Callback<List<AmmenitityModel>>() {
            @Override
            public void onResponse(Call<List<AmmenitityModel>> call, Response<List<AmmenitityModel>> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<List<AmmenitityModel>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }



    public void addPaymentType(final String type, final APIResponseCallback callback){

        PaymentModel model = new PaymentModel();
        model.setName(type);

        Call<PaymentModel> call = endPoints.newPaymentType(model);

        call.enqueue(new Callback<PaymentModel>() {
            @Override
            public void onResponse(Call<PaymentModel> call, Response<PaymentModel> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<PaymentModel> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });

    }


    public void getPaymentType( final APIResponseCallback callback){


        Call<List<PaymentModel>> call = endPoints.getPaymentTypes();

        call.enqueue(new Callback<List<PaymentModel>>() {
            @Override
            public void onResponse(Call<List<PaymentModel>> call, Response<List<PaymentModel>> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<List<PaymentModel>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }


}
