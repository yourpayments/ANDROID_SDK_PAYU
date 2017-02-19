package com.payu.payusdk.protocols;

import java.util.Map;
import java.util.TreeMap;

abstract class BaseRequestBuilder {

    String secretKey;
    Map<String, String> dataMap;

    BaseRequestBuilder(String secretKey) {
        this.secretKey = secretKey;
        dataMap = new TreeMap<>();
    }

    @Override
    public String toString() {
        return dataMap.toString();
    }

    static class RestKeys {

        static final String ORDER_HASH = "ORDER_HASH";

        static final String MERCHANT = "MERCHANT";
        static final String ORDER_REF = "ORDER_REF";
        static final String ORDER_DATE = "ORDER_DATE";
        static final String PAY_METHOD = "PAY_METHOD";
        static final String ORDER_SHIPPING = "ORDER_SHIPPING";
        static final String PRICES_CURRENCY = "PRICES_CURRENCY";
        static final String LANGUAGE = "LANGUAGE";

        static final String BILL_FIRST_NAME = "BILL_FNAME";
        static final String BILL_LAST_NAME = "BILL_LNAME";
        static final String BILL_EMAIL = "BILL_EMAIL";
        static final String BILL_PHONE = "BILL_PHONE";
        static final String BILL_COUNTRY_CODE = "BILL_COUNTRYCODE";

        static final String TEST_ORDER = "TESTORDER";

        static final String ORDER_PRODUCT_NAME = "ORDER_PNAME[]";
        static final String ORDER_PRODUCT_CODE = "ORDER_PCODE[]";
        static final String ORDER_PRODUCT_PRICE = "ORDER_PRICE[]";
        static final String ORDER_PRODUCT_QUANTITY = "ORDER_QTY[]";
        static final String ORDER_PRODUCT_VAT = "ORDER_VAT[]";
        static final String ORDER_PRODUCT_GROUP = "ORDER_PGROUP[]";
        static final String ORDER_PRODUCT_INFO = "ORDER_PINFO[]";

        static final String ORDER_AMOUNT = "ORDER_AMOUNT";
        static final String ORDER_CURRENCY = "ORDER_CURRENCY";
    }
}