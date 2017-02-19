package com.payu.payusdk.models.alu;

import android.os.Parcel;
import android.os.Parcelable;

public class ALUProduct implements Parcelable {

    public static final Creator<ALUProduct> CREATOR = new Creator<ALUProduct>() {
        @Override
        public ALUProduct createFromParcel(Parcel in) {
            return new ALUProduct(in);
        }

        @Override
        public ALUProduct[] newArray(int size) {
            return new ALUProduct[size];
        }
    };

    private String name;
    private String code;
    private double price;
    private int quantity;
    private String info;
    private String version;

    public ALUProduct(String name, String code, double price, int quantity, String info, String version) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
        this.info = info;
        this.version = version;
    }

    private ALUProduct(Parcel in) {
        name = in.readString();
        code = in.readString();
        price = in.readDouble();
        quantity = in.readInt();
        info = in.readString();
        version = in.readString();
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
        dest.writeString(info);
        dest.writeString(version);
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}