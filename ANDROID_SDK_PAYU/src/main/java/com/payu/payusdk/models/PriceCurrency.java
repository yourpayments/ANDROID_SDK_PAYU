package com.payu.payusdk.models;

import android.os.Parcel;
import android.os.Parcelable;

public enum PriceCurrency implements Parcelable {

    RUB,
    EUR,
    USD;

    PriceCurrency() {
    }

    public static final Parcelable.Creator<PriceCurrency> CREATOR = new Creator<PriceCurrency>() {
        @Override
        public PriceCurrency createFromParcel(Parcel in) {
            return PriceCurrency.values()[in.readInt()];
        }

        @Override
        public PriceCurrency[] newArray(int size) {
            return new PriceCurrency[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(ordinal());
    }
}