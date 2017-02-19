package com.payu.payusdk.models.irn;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import static com.payu.payusdk.models.irn.IRNResponse.RestKeys.ROOT_TAG;

@Root(name = ROOT_TAG)
public class IRNResponse implements Parcelable {

    public static final Creator<IRNResponse> CREATOR = new Creator<IRNResponse>() {
        @Override
        public IRNResponse createFromParcel(Parcel in) {
            return new IRNResponse(in);
        }

        @Override
        public IRNResponse[] newArray(int size) {
            return new IRNResponse[size];
        }
    };

    @Text(required = false)
    private String responseMessage;

    public IRNResponse() {
    }

    IRNResponse(Parcel in) {
        responseMessage = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(responseMessage);
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public void setResponseMessage(String value) {
        this.responseMessage = value;
    }

    static class RestKeys {

        static final String ROOT_TAG = "EPAYMENT";
    }
}