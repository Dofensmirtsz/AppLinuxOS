package com.example.gebruiker.applinuxos.data.remote;

import com.example.gebruiker.applinuxos.data.model.Light;
import com.example.gebruiker.applinuxos.data.model.LightsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Gebruiker on 25-3-2018.
 */

public interface LightService {

    String API = "/";

    @GET(API + "getLight/{id}")
    Call<Light> getLight(@Path(value = "id") String id);

    @GET(API + "updateLight/{id}/{newStatus}")
    Call<LightsResponse> setLightStatus(@Path(value = "id") String id, @Path(value = "newStatus") boolean value);
}
