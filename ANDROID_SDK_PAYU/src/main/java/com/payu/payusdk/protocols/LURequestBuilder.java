package com.payu.payusdk.protocols;

import android.text.TextUtils;

import com.payu.payusdk.models.CountryCode;
import com.payu.payusdk.models.LanguageType;
import com.payu.payusdk.models.PayMethodType;
import com.payu.payusdk.models.PriceCurrency;
import com.payu.payusdk.models.lu.LUProduct;
import com.payu.payusdk.utils.DateUtils;
import com.payu.payusdk.utils.EncodeUtils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
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
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_REF;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.ORDER_SHIPPING;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.PAY_METHOD;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.PRICES_CURRENCY;
import static com.payu.payusdk.protocols.BaseRequestBuilder.RestKeys.TEST_ORDER;
import static com.payu.payusdk.protocols.LURequestBuilder.RestKeys.DEBUG;
import static com.payu.payusdk.protocols.LURequestBuilder.RestKeys.DISCOUNT;
import static com.payu.payusdk.protocols.LURequestBuilder.RestKeys.ORDER_PRODUCT_CODE_$;
import static com.payu.payusdk.protocols.LURequestBuilder.RestKeys.ORDER_PRODUCT_GROUP_$;
import static com.payu.payusdk.protocols.LURequestBuilder.RestKeys.ORDER_PRODUCT_INFO_$;
import static com.payu.payusdk.protocols.LURequestBuilder.RestKeys.ORDER_PRODUCT_NAME_$;
import static com.payu.payusdk.protocols.LURequestBuilder.RestKeys.ORDER_PRODUCT_PRICE_$;
import static com.payu.payusdk.protocols.LURequestBuilder.RestKeys.ORDER_PRODUCT_QUANTITY_$;
import static com.payu.payusdk.protocols.LURequestBuilder.RestKeys.ORDER_PRODUCT_VAT_$;
import static com.payu.payusdk.protocols.LURequestBuilder.RestKeys.ORDER_TIMEOUT;
import static com.payu.payusdk.protocols.LURequestBuilder.RestKeys.TIMEOUT_URL;

public class LURequestBuilder extends BaseRequestBuilder {

    private List<LUProduct> productsList;

    public LURequestBuilder(String secretKey) {
        super(secretKey);
        dataMap = new LinkedHashMap<>();
        productsList = Collections.emptyList();
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

    public LURequestBuilder addProducts(List<LUProduct> productsList) {
        this.productsList = productsList;

        for (int i = 0; i < productsList.size(); i++) {
            LUProduct product = productsList.get(i);

            if (!TextUtils.isEmpty(product.getName())) {
                dataMap.put(String.format(ORDER_PRODUCT_NAME_$, i), product.getName());
            }

            if (!TextUtils.isEmpty(product.getPgGroup())) {
                dataMap.put(String.format(ORDER_PRODUCT_GROUP_$, i), product.getPgGroup());
            }

            if (!TextUtils.isEmpty(product.getCode())) {
                dataMap.put(String.format(ORDER_PRODUCT_CODE_$, i), product.getCode());
            }

            if (!TextUtils.isEmpty(product.getInfo())) {
                dataMap.put(String.format(ORDER_PRODUCT_INFO_$, i), product.getInfo());
            }

            if (product.getPrice() != 0) {
                dataMap.put(String.format(ORDER_PRODUCT_PRICE_$, i), String.valueOf(product.getPrice()));
            }

            if (product.getQuantity() != 0) {
                dataMap.put(String.format(ORDER_PRODUCT_QUANTITY_$, i), String.valueOf(product.getQuantity()));
            }

            if (!TextUtils.isEmpty(product.getVat())) {
                dataMap.put(String.format(ORDER_PRODUCT_VAT_$, i), product.getVat());
            }
        }

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

    public String build() {
        if (!dataMap.containsKey(ORDER_DATE)) {
            dataMap.put(ORDER_DATE, DateUtils.getCurrentDateUTC());
        }

        String hashDataString = createHashDataString();
        dataMap.put(ORDER_HASH, EncodeUtils.encodeString(hashDataString, secretKey));

        return createLinkedDataString();
    }

    public double getPurchasePrice() {
        int sum = 0;

        for (int i = 0; i < productsList.size(); ++i) {
            LUProduct product = productsList.get(i);
            sum += product.getPrice();
        }

        return sum;
    }

    private String createHashDataString() {
        StringBuilder stringBuilder = new StringBuilder();

        addHashPair(stringBuilder, dataMap.get(MERCHANT));
        addHashPair(stringBuilder, dataMap.get(ORDER_REF));
        addHashPair(stringBuilder, dataMap.get(ORDER_DATE));

        addProductHashPairs(stringBuilder);

        addHashPair(stringBuilder, dataMap.get(ORDER_SHIPPING));
        addHashPair(stringBuilder, dataMap.get(PRICES_CURRENCY));
        addHashPair(stringBuilder, dataMap.get(PAY_METHOD));

        addTestOrderHashPair(stringBuilder);

        return stringBuilder.toString();
    }

    private void addHashPair(StringBuilder stringBuilder, String value) {
        if (value != null) {
            stringBuilder.append(value.getBytes().length);
            stringBuilder.append(value);
        }
    }

    private void addProductHashPairs(StringBuilder stringBuilder) {
        if (!productsList.isEmpty()) {
            // add all NAMES
            for (int i = 0; i < productsList.size(); i++) {
                LUProduct product = productsList.get(i);
                addHashPair(stringBuilder, product.getName());
            }

            // add all CODES
            for (int i = 0; i < productsList.size(); i++) {
                LUProduct product = productsList.get(i);
                addHashPair(stringBuilder, product.getCode());
            }

            // add all INFOS
            for (int i = 0; i < productsList.size(); i++) {
                LUProduct product = productsList.get(i);
                addHashPair(stringBuilder, product.getInfo());
            }

            // add all PRICES
            for (int i = 0; i < productsList.size(); i++) {
                LUProduct product = productsList.get(i);
                addHashPair(stringBuilder, String.valueOf(product.getPrice()));
            }

            // add all QUANTITIES
            for (int i = 0; i < productsList.size(); i++) {
                LUProduct product = productsList.get(i);
                addHashPair(stringBuilder, String.valueOf(product.getQuantity()));
            }

            // add all VATS
            for (int i = 0; i < productsList.size(); i++) {
                LUProduct product = productsList.get(i);
                addHashPair(stringBuilder, product.getVat());
            }
        }
    }

    private void addTestOrderHashPair(StringBuilder stringBuilder) {
        if (dataMap.containsKey(TEST_ORDER) && dataMap.get(TEST_ORDER).equalsIgnoreCase(Boolean.TRUE.toString())) {
            addHashPair(stringBuilder, dataMap.get(TEST_ORDER));
        }
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

        static final String ORDER_PRODUCT_NAME_$ = "ORDER_PNAME[%1$d]";
        static final String ORDER_PRODUCT_CODE_$ = "ORDER_PCODE[%1$d]";
        static final String ORDER_PRODUCT_PRICE_$ = "ORDER_PRICE[%1$d]";
        static final String ORDER_PRODUCT_QUANTITY_$ = "ORDER_QTY[%1$d]";
        static final String ORDER_PRODUCT_VAT_$ = "ORDER_VAT[%1$d]";
        static final String ORDER_PRODUCT_GROUP_$ = "ORDER_PGROUP[%1$d]";
        static final String ORDER_PRODUCT_INFO_$ = "ORDER_PINFO[%1$d]";
    }
}