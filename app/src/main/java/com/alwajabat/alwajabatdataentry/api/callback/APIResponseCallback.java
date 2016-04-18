package com.alwajabat.alwajabatdataentry.api.callback;



import retrofit2.Response;

/**
 * Created by deezdroid on 18/04/16.
 */
public interface APIResponseCallback {

    public void onSuccess(Response response );
    public void onFailure(String error);
}
