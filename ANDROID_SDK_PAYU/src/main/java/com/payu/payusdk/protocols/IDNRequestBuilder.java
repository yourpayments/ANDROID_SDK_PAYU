package com.payu.payusdk.protocols;

import com.payu.payusdk.models.PriceCurrency;
import com.payu.payusdk.utils.EncodeUtils;

import java.util.Map;

import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.MERCHANT;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_AMOUNT;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_CURRENCY;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_HASH;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_REF;
import static com.payu.payusdk.protocols.IDNRequestBuilder.RestKeys.IDN_DATE;

public class IDNRequestBuilder extends BaseRequestBuilder {

    public IDNRequestBuilder(String secretKey) {
        super(secretKey);
        this.secretKey = secretKey;
    }

    public IDNRequestBuilder setMerchantId(String merchant) {
        dataMap.put(MERCHANT, merchant);

        return this;
    }

    public IDNRequestBuilder setOrderExternalNumber(String number) {
        dataMap.put(ORDER_REF, number);

        return this;
    }

    public IDNRequestBuilder setOrderAmount(String amount) {
        dataMap.put(ORDER_AMOUNT, amount);

        return this;
    }

    public IDNRequestBuilder setOrderCurrency(PriceCurrency currency) {
        dataMap.put(ORDER_CURRENCY, currency.name());

        return this;
    }

    public IDNRequestBuilder setIdnDate(String date) {
        dataMap.put(IDN_DATE, date);

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
            stringBuilder.append(value.getBytes().length);
            stringBuilder.append(value);
        }

        return stringBuilder.toString();
    }

    static class RestKeys {

        static final String IDN_DATE = "IDN_DATE";
    }
}