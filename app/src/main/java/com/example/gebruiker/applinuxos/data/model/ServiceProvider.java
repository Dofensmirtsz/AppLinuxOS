package com.example.gebruiker.applinuxos.data.model;

import android.util.Log;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {

    private static ServiceProvider instance;
    public String BASE_URL = "http://10.0.2.2:8081";

    public static ServiceProvider instance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }

    private Retrofit.Builder builder;
    private Retrofit retrofit;
    private OkHttpClient.Builder httpClient;

    private ServiceProvider(){

    }

    public <S> S createService(
            Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    public void createServiceProvider(){
        Log.d("BASEURL", "Creating ServiceProvider @" + BASE_URL);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
    }

}

