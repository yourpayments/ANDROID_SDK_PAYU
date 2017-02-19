package com.payu.payusdk.models.alu;

import android.os.Parcel;
import android.os.Parcelable;

import com.payu.payusdk.models.CountryCode;

public class ALUDeliveryData implements Parcelable {

    public static final Creator<ALUDeliveryData> CREATOR = new Creator<ALUDeliveryData>() {
        @Override
        public ALUDeliveryData createFromParcel(Parcel in) {
            return new ALUDeliveryData(in);
        }

        @Override
        public ALUDeliveryData[] newArray(int size) {
            return new ALUDeliveryData[size];
        }
    };

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String zipCode;
    private String city;
    private String state;
    private CountryCode countryCode;
    private String company;
    private String address2;

    public ALUDeliveryData() {
    }

    private ALUDeliveryData(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        phone = in.readString();
        address = in.readString();
        zipCode = in.readString();
        city = in.readString();
        state = in.readString();
        countryCode = in.readParcelable(CountryCode.class.getClassLoader());
        company = in.readString();
        address2 = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(address);
        dest.writeString(zipCode);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeParcelable(countryCode, flags);
        dest.writeString(company);
        dest.writeString(address2);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
}