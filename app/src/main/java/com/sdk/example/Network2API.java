package com.sdk.example;


import com.google.gson.JsonObject;
import com.sdk.example.data.savetoken.SaveTokenRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by FaridGamarra on 10/5/20.
 * OTC
 * farid_gamarra@gmail.com
 */
public interface Network2API {

    public static final String BASE_URL = "https://sc5v2x7207.execute-api.us-east-1.amazonaws.com";

    @Headers({"Content-Type: application/json"})
    @POST("tokenization/v1/savetoken")
    Call<JsonObject> callSaveToken(
            @Body SaveTokenRequest request);




}
