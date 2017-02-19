package com.payu.payusdk.models.alu;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import static com.payu.payusdk.models.alu.ALUResponse.RestKeys.ROOT_TAG;
import static com.payu.payusdk.models.alu.ALUResponse.RestKeys.TAG_ALIAS;
import static com.payu.payusdk.models.alu.ALUResponse.RestKeys.TAG_AUTH_CODE;
import static com.payu.payusdk.models.alu.ALUResponse.RestKeys.TAG_DATE;
import static com.payu.payusdk.models.alu.ALUResponse.RestKeys.TAG_HASH;
import static com.payu.payusdk.models.alu.ALUResponse.RestKeys.TAG_ORDER_REF;
import static com.payu.payusdk.models.alu.ALUResponse.RestKeys.TAG_REFNO;
import static com.payu.payusdk.models.alu.ALUResponse.RestKeys.TAG_RETURN_CODE;
import static com.payu.payusdk.models.alu.ALUResponse.RestKeys.TAG_RETURN_MESSAGE;
import static com.payu.payusdk.models.alu.ALUResponse.RestKeys.TAG_RRN;
import static com.payu.payusdk.models.alu.ALUResponse.RestKeys.TAG_STATUS;

@Root(name = ROOT_TAG)
public class ALUResponse implements Parcelable {

    public static final Creator<ALUResponse> CREATOR = new Creator<ALUResponse>() {
        @Override
        public ALUResponse createFromParcel(Parcel in) {
            return new ALUResponse(in);
        }

        @Override
        public ALUResponse[] newArray(int size) {
            return new ALUResponse[size];
        }
    };

    @Element(name = TAG_REFNO, required = false)
    private String refno;

    @Element(name = TAG_ALIAS, required = false)
    private String alias;

    @Element(name = TAG_STATUS, required = false)
    private String status;

    @Element(name = TAG_RETURN_CODE, required = false)
    private String code;

    @Element(name = TAG_RETURN_MESSAGE, required = false)
    private String message;

    @Element(name = TAG_DATE, required = false)
    private String date;

    @Element(name = TAG_ORDER_REF, required = false)
    private String orderRef;

    @Element(name = TAG_AUTH_CODE, required = false)
    private String authCode;

    @Element(name = TAG_RRN, required = false)
    private String rrn;

    @Element(name = TAG_HASH, required = false)
    private String hash;

    public ALUResponse() {
    }

    private ALUResponse(Parcel in) {
        refno = in.readString();
        alias = in.readString();
        status = in.readString();
        code = in.readString();
        message = in.readString();
        date = in.readString();
        orderRef = in.readString();
        authCode = in.readString();
        rrn = in.readString();
        hash = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(refno);
        dest.writeString(alias);
        dest.writeString(status);
        dest.writeString(code);
        dest.writeString(message);
        dest.writeString(date);
        dest.writeString(orderRef);
        dest.writeString(authCode);
        dest.writeString(rrn);
        dest.writeString(hash);
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "ALUResponse{" +
                "refno='" + refno + '\'' +
                ", alias='" + alias + '\'' +
                ", status='" + status + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                ", orderRef='" + orderRef + '\'' +
                ", authCode='" + authCode + '\'' +
                ", rrn='" + rrn + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }

    static class RestKeys {

        static final String ROOT_TAG = "EPAYMENT";
        static final String TAG_REFNO = "REFNO";
        static final String TAG_ALIAS = "ALIAS";
        static final String TAG_STATUS = "STATUS";
        static final String TAG_RETURN_CODE = "RETURN_CODE";
        static final String TAG_RETURN_MESSAGE = "RETURN_MESSAGE";
        static final String TAG_DATE = "DATE";
        static final String TAG_ORDER_REF = "ORDER_REF";
        static final String TAG_AUTH_CODE = "AUTH_CODE";
        static final String TAG_RRN = "RRN";
        static final String TAG_HASH = "HASH";
    }
}