package com.payu.payusdk.protocols;

import com.payu.payusdk.utils.EncodeUtils;

import java.util.Map;

import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.MERCHANT;
import static com.payu.payusdk.protocols.IOSRequestBuilder.RestKeys.HASH;
import static com.payu.payusdk.protocols.IOSRequestBuilder.RestKeys.REFNOEXT;

public class IOSRequestBuilder extends BaseRequestBuilder {

    public IOSRequestBuilder(String secretKey) {
        super(secretKey);
        this.secretKey = secretKey;
    }

    public IOSRequestBuilder setMerchantId(String merchant) {
        dataMap.put(MERCHANT, merchant);

        return this;
    }

    public IOSRequestBuilder setRefnoext(String refnoext) {
        dataMap.put(REFNOEXT, refnoext);

        return this;
    }

    public Map<String, String> build() {
        String dataString = createHashDataString();
        dataMap.put(HASH, EncodeUtils.encodeString(dataString, secretKey));

        return dataMap;
    }

    private String createHashDataString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String value : dataMap.values()) {
            stringBuilder.append(value.length());
            stringBuilder.append(value);
        }

        return stringBuilder.toString();
    }

    static class RestKeys {

        static final String HASH = "HASH";
        static final String REFNOEXT = "REFNOEXT";
    }
}