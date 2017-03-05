package com.payu.payusdk.protocols;

import android.text.TextUtils;

import com.payu.payusdk.models.CountryCode;
import com.payu.payusdk.models.LanguageType;
import com.payu.payusdk.models.PayMethodType;
import com.payu.payusdk.models.PriceCurrency;
import com.payu.payusdk.models.lu.LUProduct;
import com.payu.payusdk.utils.DateUtils;
import com.payu.payusdk.utils.EncodeUtils;

import java.util.Map;

import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.BILL_COUNTRY_CODE;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.BILL_EMAIL;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.BILL_FIRST_NAME;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.BILL_LAST_NAME;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.BILL_PHONE;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.LANGUAGE;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.MERCHANT;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_DATE;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_HASH;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_PRODUCT_CODE;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_PRODUCT_GROUP;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_PRODUCT_INFO;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_PRODUCT_NAME;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_PRODUCT_PRICE;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_PRODUCT_QUANTITY;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_PRODUCT_VAT;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_REF;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_SHIPPING;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.PAY_METHOD;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.PRICES_CURRENCY;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.TEST_ORDER;
import static com.payu.payusdk.protocols.LURequestBuilder.RestKeys.DEBUG;
import static com.payu.payusdk.protocols.LURequestBuilder.RestKeys.DISCOUNT;
import static com.payu.payusdk.protocols.LURequestBuilder.RestKeys.ORDER_TIMEOUT;
import static com.payu.payusdk.protocols.LURequestBuilder.RestKeys.TIMEOUT_URL;

public class LURequestBuilder extends BaseRequestBuilder {

    public LURequestBuilder(String secretKey) {
        super(secretKey);
    }

    // -- START required fields ---
    public LURequestBuilder setMerchantId(String merchant) {
        dataMap.put(MERCHANT, merchant);

        return this;
    }

    public LURequestBuilder setOrderExternalNumber(String number) {
        dataMap.put(ORDER_REF, number);

        return this;
    }

    public LURequestBuilder setOrderDate(String date) {
        dataMap.put(ORDER_DATE, date);

        return this;
    }
    // -- END required fields ---

    public LURequestBuilder setBillFirstName(String firstName) {
        dataMap.put(BILL_FIRST_NAME, firstName);

        return this;
    }

    public LURequestBuilder setBillLastName(String lastName) {
        dataMap.put(BILL_LAST_NAME, lastName);

        return this;
    }

    public LURequestBuilder setBillEmail(String email) {
        dataMap.put(BILL_EMAIL, email);

        return this;
    }

    public LURequestBuilder setBillPhoneNumber(String phone) {
        dataMap.put(BILL_PHONE, phone);

        return this;
    }

    public LURequestBuilder setBillCountryCode(CountryCode countryCode) {
        dataMap.put(BILL_COUNTRY_CODE, countryCode.name());

        return this;
    }

    public LURequestBuilder setLanguage(LanguageType language) {
        dataMap.put(LANGUAGE, language.name());

        return this;
    }

    public LURequestBuilder setDiscount(double discount) {
        dataMap.put(DISCOUNT, String.valueOf(discount));

        return this;
    }

    public LURequestBuilder setPaymentMethod(PayMethodType method) {
        dataMap.put(PAY_METHOD, method.name());

        return this;
    }

    public LURequestBuilder setOrderShipping(long shipping) {
        dataMap.put(ORDER_SHIPPING, String.valueOf(shipping));

        return this;
    }

    public LURequestBuilder setPriceCurrency(PriceCurrency currency) {
        dataMap.put(PRICES_CURRENCY, currency.name());

        return this;
    }

    public LURequestBuilder setOrderTimeout(long timeout) {
        dataMap.put(ORDER_TIMEOUT, String.valueOf(timeout));

        return this;
    }

    public LURequestBuilder setTimeoutUrl(String url) {
        dataMap.put(TIMEOUT_URL, url);

        return this;
    }

    public LURequestBuilder setIsTestOrder(boolean enable) {
        dataMap.put(TEST_ORDER, String.valueOf(enable).toUpperCase());

        return this;
    }

    public LURequestBuilder setDebug(boolean enable) {
        dataMap.put(DEBUG, String.valueOf(enable).toUpperCase());

        return this;
    }

    public LURequestBuilder addProduct(LUProduct product) {
        if (!TextUtils.isEmpty(product.getName())) {
            dataMap.put(ORDER_PRODUCT_NAME, product.getName());
        }

        if (!TextUtils.isEmpty(product.getCode())) {
            dataMap.put(ORDER_PRODUCT_CODE, product.getCode());
        }

        if (product.getPrice() != 0) {
            dataMap.put(ORDER_PRODUCT_PRICE, String.valueOf(product.getPrice()));
        }

        if (product.getQuantity() != 0) {
            dataMap.put(ORDER_PRODUCT_QUANTITY, String.valueOf(product.getQuantity()));
        }

        if (!TextUtils.isEmpty(product.getVat())) {
            dataMap.put(ORDER_PRODUCT_VAT, product.getVat());
        }

        if (!TextUtils.isEmpty(product.getPgGroup())) {
            dataMap.put(ORDER_PRODUCT_GROUP, product.getPgGroup());
        }

        if (!TextUtils.isEmpty(product.getInfo())) {
            dataMap.put(ORDER_PRODUCT_INFO, product.getInfo());
        }

        return this;
    }

    public String build() {
        if (!dataMap.containsKey(ORDER_DATE)) {
            dataMap.put(ORDER_DATE, DateUtils.getCurrentDateUTC());
        }

        String dataString = createHashDataString();
        dataMap.put(ORDER_HASH, EncodeUtils.encodeString(dataString, secretKey));

        return createLinkedDataString();
    }

    public double getPurchasePrice() {
        if (dataMap.containsKey(ORDER_PRODUCT_PRICE)) {
            return Double.parseDouble(dataMap.get(ORDER_PRODUCT_PRICE));
        } else {
            return 0;
        }
    }

    private String createHashDataString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String value : dataMap.values()) {
            stringBuilder.append(value.length());
            stringBuilder.append(value);
        }

        return stringBuilder.toString();
    }

    private String createLinkedDataString() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean was = false;

        for (Map.Entry<String, String> value : dataMap.entrySet()) {
            if (was) {
                stringBuilder.append("&");
            }

            stringBuilder.append(value.getKey());
            stringBuilder.append("=");
            stringBuilder.append(value.getValue());

            was = true;
        }

        return stringBuilder.toString();
    }

    static class RestKeys {

        static final String DISCOUNT = "DISCOUNT";
        static final String ORDER_TIMEOUT = "ORDER_TIMEOUT";
        static final String TIMEOUT_URL = "TIMEOUT_URL";
        static final String DEBUG = "DEBUG";
    }
}