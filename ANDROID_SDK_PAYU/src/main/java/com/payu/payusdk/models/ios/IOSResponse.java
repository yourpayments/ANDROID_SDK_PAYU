package com.payu.payusdk.models.ios;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import static com.payu.payusdk.models.ios.IOSResponse.RestKeys.ROOT_TAG;
import static com.payu.payusdk.models.ios.IOSResponse.RestKeys.TAG_HASH;
import static com.payu.payusdk.models.ios.IOSResponse.RestKeys.TAG_ORDER_DATE;
import static com.payu.payusdk.models.ios.IOSResponse.RestKeys.TAG_ORDER_STATUS;
import static com.payu.payusdk.models.ios.IOSResponse.RestKeys.TAG_PAY_METHOD;
import static com.payu.payusdk.models.ios.IOSResponse.RestKeys.TAG_REFNO;
import static com.payu.payusdk.models.ios.IOSResponse.RestKeys.TAG_REFNOEXT;

@Root(name = ROOT_TAG)
public class IOSResponse implements Parcelable {

    public static final Creator<IOSResponse> CREATOR = new Creator<IOSResponse>() {
        @Override
        public IOSResponse createFromParcel(Parcel in) {
            return new IOSResponse(in);
        }

        @Override
        public IOSResponse[] newArray(int size) {
            return new IOSResponse[size];
        }
    };

    @Element(name = TAG_ORDER_DATE, required = false)
    private String orderDate;

    @Element(name = TAG_REFNO, required = false)
    private String refno;

    @Element(name = TAG_ORDER_STATUS, required = false)
    private String orderStatus;

    @Element(name = TAG_PAY_METHOD, required = false)
    private String payMethod;

    @Element(name = TAG_REFNOEXT, required = false)
    private String refnoext;

    @Element(name = TAG_HASH, required = false)
    private String hash;

    public IOSResponse() {
    }

    protected IOSResponse(Parcel in) {
        orderDate = in.readString();
        refno = in.readString();
        orderStatus = in.readString();
        payMethod = in.readString();
        refnoext = in.readString();
        hash = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderDate);
        dest.writeString(refno);
        dest.writeString(orderStatus);
        dest.writeString(payMethod);
        dest.writeString(refnoext);
        dest.writeString(hash);
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getRefnoext() {
        return refnoext;
    }

    public void setRefnoext(String refnoext) {
        this.refnoext = refnoext;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    static class RestKeys {

        static final String ROOT_TAG = "Order";
        static final String TAG_REFNO = "REFNO";
        static final String TAG_ORDER_STATUS = "ORDER_STATUS";
        static final String TAG_ORDER_DATE = "ORDER_DATE";
        static final String TAG_PAY_METHOD = "PAYMETHOD";
        static final String TAG_REFNOEXT = "REFNOEXT";
        static final String TAG_HASH = "HASH";
    }
}