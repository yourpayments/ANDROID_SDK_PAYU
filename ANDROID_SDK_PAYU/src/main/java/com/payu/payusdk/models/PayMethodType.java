package com.payu.payusdk.models;

import android.os.Parcel;
import android.os.Parcelable;

public enum PayMethodType implements Parcelable {

    CCVISAMC,
    WEBMONEY,
    QIWI,
    YANDEX,
    EUROSET_SVYAZNOI,
    ALFACLICK;

    PayMethodType() {
    }

    public static final Parcelable.Creator<PayMethodType> CREATOR = new Creator<PayMethodType>() {
        @Override
        public PayMethodType createFromParcel(Parcel in) {
            return PayMethodType.values()[in.readInt()];
        }

        @Override
        public PayMethodType[] newArray(int size) {
            return new PayMethodType[size];
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