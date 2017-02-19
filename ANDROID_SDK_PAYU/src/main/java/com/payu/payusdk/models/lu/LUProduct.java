package com.payu.payusdk.models.lu;

import android.os.Parcel;
import android.os.Parcelable;

public class LUProduct implements Parcelable {

    public static final Creator<LUProduct> CREATOR = new Creator<LUProduct>() {
        @Override
        public LUProduct createFromParcel(Parcel in) {
            return new LUProduct(in);
        }

        @Override
        public LUProduct[] newArray(int size) {
            return new LUProduct[size];
        }
    };

    private String name;
    private String code;
    private double price;
    private int quantity;
    private String vat;
    private String pgGroup;
    private String info;

    public LUProduct(String name, String code, double price, int quantity, String vat) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
        this.vat = vat;
    }

    private LUProduct(Parcel in) {
        name = in.readString();
        code = in.readString();
        price = in.readDouble();
        quantity = in.readInt();
        vat = in.readString();
        pgGroup = in.readString();
        info = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getPgGroup() {
        return pgGroup;
    }

    public void setPgGroup(String pgGroup) {
        this.pgGroup = pgGroup;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(code);
        dest.writeDouble(price);
        dest.writeInt(quantity);
        dest.writeString(vat);
        dest.writeString(pgGroup);
        dest.writeString(info);
    }
}