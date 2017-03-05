package com.payu.payusdk.models.alu;

import android.os.Parcel;
import android.os.Parcelable;

public class ALUCardInfo implements Parcelable {

    public static final Creator<ALUCardInfo> CREATOR = new Creator<ALUCardInfo>() {
        @Override
        public ALUCardInfo createFromParcel(Parcel in) {
            return new ALUCardInfo(in);
        }

        @Override
        public ALUCardInfo[] newArray(int size) {
            return new ALUCardInfo[size];
        }
    };

    private String ccNumber;
    private String expMonth;
    private String expYear;
    private String ccCVV;
    private String ccOwner;
    private String ccToken;

    public ALUCardInfo(String ccNumber, String expMonth, String expYear, String ccCVV, String ccOwner) {
        this.ccNumber = ccNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.ccCVV = ccCVV;
        this.ccOwner = ccOwner;
    }

    private ALUCardInfo(Parcel in) {
        ccNumber = in.readString();
        expMonth = in.readString();
        expYear = in.readString();
        ccCVV = in.readString();
        ccOwner = in.readString();
        ccToken = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ccNumber);
        dest.writeString(expMonth);
        dest.writeString(expYear);
        dest.writeString(ccCVV);
        dest.writeString(ccOwner);
        dest.writeString(ccToken);
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public String getCcCVV() {
        return ccCVV;
    }

    public void setCcCVV(String ccCVV) {
        this.ccCVV = ccCVV;
    }

    public String getCcOwner() {
        return ccOwner;
    }

    public void setCcOwner(String ccOwner) {
        this.ccOwner = ccOwner;
    }

    public String getCcToken() {
        return ccToken;
    }

    public void setCcToken(String ccToken) {
        this.ccToken = ccToken;
    }
}