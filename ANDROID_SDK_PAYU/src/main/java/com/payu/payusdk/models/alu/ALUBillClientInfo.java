package com.payu.payusdk.models.alu;

import android.os.Parcel;
import android.os.Parcelable;

import com.payu.payusdk.models.CountryCode;

public class ALUBillClientInfo implements Parcelable {

    public static final Creator<ALUBillClientInfo> CREATOR = new Creator<ALUBillClientInfo>() {
        @Override
        public ALUBillClientInfo createFromParcel(Parcel in) {
            return new ALUBillClientInfo(in);
        }

        @Override
        public ALUBillClientInfo[] newArray(int size) {
            return new ALUBillClientInfo[size];
        }
    };

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private CountryCode countryCode;

    // optional fields
    private String fax;
    private String address;
    private String address2;
    private String zipCode;
    private String city;
    private String state;

    public ALUBillClientInfo(String firstName, String lastName, String email, String phone,
            CountryCode countryCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.countryCode = countryCode;
    }

    private ALUBillClientInfo(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        phone = in.readString();
        countryCode = in.readParcelable(CountryCode.class.getClassLoader());
        fax = in.readString();
        address = in.readString();
        address2 = in.readString();
        zipCode = in.readString();
        city = in.readString();
        state = in.readString();
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
        dest.writeParcelable(countryCode, flags);
        dest.writeString(fax);
        dest.writeString(address);
        dest.writeString(address2);
        dest.writeString(zipCode);
        dest.writeString(city);
        dest.writeString(state);
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

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
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
}