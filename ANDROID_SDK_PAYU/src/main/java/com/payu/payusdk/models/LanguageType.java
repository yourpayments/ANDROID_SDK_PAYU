package com.payu.payusdk.models;

import android.os.Parcel;
import android.os.Parcelable;

public enum LanguageType implements Parcelable {

    EN,
    RO,
    HU,
    RU,
    DE,
    FR,
    IT,
    ES;

    LanguageType() {
    }

    public static final Parcelable.Creator<LanguageType> CREATOR = new Creator<LanguageType>() {
        @Override
        public LanguageType createFromParcel(Parcel in) {
            return LanguageType.values()[in.readInt()];
        }

        @Override
        public LanguageType[] newArray(int size) {
            return new LanguageType[size];
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