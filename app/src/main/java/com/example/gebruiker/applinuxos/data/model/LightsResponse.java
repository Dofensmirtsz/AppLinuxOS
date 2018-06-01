package com.example.gebruiker.applinuxos.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class LightsResponse implements Parcelable {

    ArrayList<Light> lights;

    public LightsResponse(ArrayList<Light> lights) {
        this.lights = lights;
    }

    public ArrayList<Light> getLights() {
        return lights;
    }

    public void setLights(ArrayList<Light> lights) {
        this.lights = lights;
    }

    protected LightsResponse(Parcel in) {
        if (in.readByte() == 0x01) {
            lights = new ArrayList<Light>();
            in.readList(lights, Light.class.getClassLoader());
        } else {
            lights = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (lights == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(lights);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<LightsResponse> CREATOR = new Parcelable.Creator<LightsResponse>() {
        @Override
        public LightsResponse createFromParcel(Parcel in) {
            return new LightsResponse(in);
        }

        @Override
        public LightsResponse[] newArray(int size) {
            return new LightsResponse[size];
        }
    };
}