package com.payu.payusdk.protocols;

import com.payu.payusdk.models.PriceCurrency;
import com.payu.payusdk.utils.EncodeUtils;

import java.util.Map;

import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.MERCHANT;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_AMOUNT;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_CURRENCY;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_HASH;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_REF;
import static com.payu.payusdk.protocols.IRNRequestBuilder.RestKeys.AMOUNT;
import static com.payu.payusdk.protocols.IRNRequestBuilder.RestKeys.IRN_DATE;

public class IRNRequestBuilder extends BaseRequestBuilder {

    public IRNRequestBuilder(String secretKey) {
        super(secretKey);
        this.secretKey = secretKey;
    }

    // -- START required fields ---
    public IRNRequestBuilder setMerchantId(String merchant) {
        dataMap.put(MERCHANT, merchant);

        return this;
    }

    public IRNRequestBuilder setOrderExternalNumber(String number) {
        dataMap.put(ORDER_REF, number);

        return this;
    }

    public IRNRequestBuilder setOrderAmount(String amount) {
        dataMap.put(ORDER_AMOUNT, amount);

        return this;
    }

    public IRNRequestBuilder setOrderCurrency(PriceCurrency currency) {
        dataMap.put(ORDER_CURRENCY, currency.name());

        return this;
    }

    public IRNRequestBuilder setIrnDate(String date) {
        dataMap.put(IRN_DATE, date);

        return this;
    }
    // -- END required fields ---

    public IRNRequestBuilder setAmount(String amount) {
        dataMap.put(AMOUNT, amount);

        return this;
    }

    public Map<String, String> build() {
        String dataString = createHashDataString();
        dataMap.put(ORDER_HASH, EncodeUtils.encodeString(dataString, secretKey));

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

        static final String IRN_DATE = "IRN_DATE";
        static final String AMOUNT = "AMOUNT";
    }
}