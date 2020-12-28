package com.sdk.example;

import com.google.gson.JsonObject;
import com.sdk.example.data.authorization.request.AuthorizationRequest;
import com.sdk.example.data.token.response.AccessTokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by FaridGamarra on 10/5/20.
 * OTC
 * farid_gamarra@gmail.com
 */
public interface NetworkAPI {

    public static final String BASE_URL = "https://apiqa.ecore.mobi";

    @GET("api.security/v2/{tenantId}/security/ecommerce/{merchantId}")
    Call<AccessTokenResponse> callAccessToken(
            @Header("Authorization") String authBasic,
            @Path("tenantId") String tenantId,
            @Path("merchantId") String merchantId);


    @Headers({"Content-Type: application/json"})
    @POST("api.authorization/v3/{tenantId}/authorize")
    Call<JsonObject> callAuthorization(
            @Header("Authorization") String auth,
            @Path("tenantId") String tenantId,
            @Body AuthorizationRequest request);

}
