package com.payu.payusdk.models.idn;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import static com.payu.payusdk.models.idn.IDNResponse.RestKeys.ROOT_TAG;

@Root(name = ROOT_TAG)
public class IDNResponse implements Parcelable {

    public static final Creator<IDNResponse> CREATOR = new Creator<IDNResponse>() {
        @Override
        public IDNResponse createFromParcel(Parcel in) {
            return new IDNResponse(in);
        }

        @Override
        public IDNResponse[] newArray(int size) {
            return new IDNResponse[size];
        }
    };

    @Text(required = false)
    private String responseMessage;

    public IDNResponse() {
    }

    IDNResponse(Parcel in) {
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