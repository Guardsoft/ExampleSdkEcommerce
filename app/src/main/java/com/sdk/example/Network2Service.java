package com.sdk.example;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sdk.example.Network2API.BASE_URL;


/**
 * Created by FaridGamarra on 10/5/20.
 * OTC
 * farid_gamarra@gmail.com
 */
public class Network2Service {

    private Network2API networkAPI;
    private OkHttpClient okHttpClient;
    private Context mContext;

    public Network2Service(Context context) {
        this(BASE_URL);
        this.mContext = context;
    }

    public Network2Service(String baseUrl) {

        okHttpClient = buildClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        networkAPI = retrofit.create(Network2API.class);
    }


    /**
     * Getting API Interface
     * @return api interface
     */
    public Network2API getAPI() {
        return networkAPI;
    }


    public OkHttpClient buildClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.connectTimeout(30, TimeUnit.SECONDS);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //logging.level(HttpLoggingInterceptor.Level.BASIC);
        logging.level(HttpLoggingInterceptor.Level.BODY);
        //logging.level(HttpLoggingInterceptor.Level.HEADERS);
        builder.addInterceptor(logging);

        return builder.build();
    }

}
