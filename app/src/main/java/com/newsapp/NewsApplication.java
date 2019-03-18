package com.newsapp;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import network.Api;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import util.Constants;
import util.Util;

/**
 * Created by Tejraj on 17-Mar-19.
 */

public class NewsApplication extends Application {
    static OkHttpClient newApiClient;
    private static Api api;

    @Override
    public void onCreate() {
        super.onCreate();

        initRetrofit();
    }

    private void initRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor2 = new HttpLoggingInterceptor();
        httpLoggingInterceptor2.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okb = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor2)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request request2 = request.newBuilder().addHeader(Util.getAuthKeyword(), Constants.API_AUTH_TYPE_JWT + " " + Constants.API_KEY).build();
                        return chain.proceed(request2);
                    }
                }).connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        //init client
        newApiClient = okb.build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(Util.getNewsBaseUrl())
                .client(newApiClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = mRetrofit.create(Api.class);
        //network code end
    }

    public static Api getApi(){
        return api;
    }
}
