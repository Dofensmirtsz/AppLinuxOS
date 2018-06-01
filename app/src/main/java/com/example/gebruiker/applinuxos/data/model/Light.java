package com.example.gebruiker.applinuxos.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gebruiker status 25-3-2018.
 */

public class Light implements Parcelable {

    String id;
    boolean status;

    public Light(String id, boolean status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    protected Light(Parcel in) {
        id = in.readString();
        status = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeByte((byte) (status ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Light> CREATOR = new Parcelable.Creator<Light>() {
        @Override
        public Light createFromParcel(Parcel in) {
            return new Light(in);
        }

        @Override
        public Light[] newArray(int size) {
            return new Light[size];
        }
    };
}