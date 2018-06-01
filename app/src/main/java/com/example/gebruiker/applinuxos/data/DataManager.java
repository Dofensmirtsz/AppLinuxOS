package com.example.gebruiker.applinuxos.data;

import android.content.Context;

import com.example.gebruiker.applinuxos.data.model.Light;
import com.example.gebruiker.applinuxos.data.model.LightsResponse;
import com.example.gebruiker.applinuxos.data.model.ServiceProvider;
import com.example.gebruiker.applinuxos.data.remote.LightService;

import retrofit2.Call;

/**
 * Created by Gebruiker on 25-3-2018.
 */

public class DataManager {

    private static DataManager instance;
    private final LightService lightService;

    public static DataManager instance(Context context) {
        if (instance == null) {
            instance = new DataManager(context);
        }
        return instance;
    }

    private DataManager(Context context) {
        lightService = ServiceProvider.instance().createService(LightService.class);
    }

    public Call<Light> getLight(String id){
        return lightService.getLight(id);
    }

    public Call<LightsResponse> setLightStatus(Light light){
        return lightService.setLightStatus(light.getId(), light.isStatus());
    }

}
