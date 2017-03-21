package com.payu.payusdk.protocols;

import android.text.TextUtils;

import com.payu.payusdk.models.LanguageType;
import com.payu.payusdk.models.PayMethodType;
import com.payu.payusdk.models.PriceCurrency;
import com.payu.payusdk.models.alu.ALUBillClientInfo;
import com.payu.payusdk.models.alu.ALUCardInfo;
import com.payu.payusdk.models.alu.ALUDeliveryData;
import com.payu.payusdk.models.alu.ALUProduct;
import com.payu.payusdk.utils.DateUtils;
import com.payu.payusdk.utils.EncodeUtils;

import java.util.List;
import java.util.Map;

import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.BACK_REF;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.BILL_ADDRESS;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.BILL_ADDRESS2;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.BILL_CITY;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.BILL_FAX;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.BILL_STATE;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.BILL_ZIP_CODE;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.CARD_CC_CVV;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.CARD_CC_NUMBER;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.CARD_CC_OWNER;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.CARD_CC_TOKEN;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.CARD_EXP_MONTH;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.CARD_EXP_YEAR;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.CC_NUMBER_TIME;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.CC_OWNER_TIME;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.CLIENT_IP;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.CLIENT_TIME;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.DELIVERY_DATA_ADDRESS;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.DELIVERY_DATA_ADDRESS2;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.DELIVERY_DATA_CITY;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.DELIVERY_DATA_COMPANY;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.DELIVERY_DATA_COUNTRY_CODE;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.DELIVERY_DATA_EMAIL;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.DELIVERY_DATA_FIRST_NAME;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.DELIVERY_DATA_LAST_NAME;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.DELIVERY_DATA_PHONE;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.DELIVERY_DATA_STATE;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.DELIVERY_DATA_ZIP_CODE;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.ORDER_PCODE_$;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.ORDER_PINFO_$;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.ORDER_PNAME_$;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.ORDER_PRICE_$;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.ORDER_QTY_$;
import static com.payu.payusdk.protocols.ALURequestBuilder.RestKeys.ORDER_VER_$;
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
import static com.payu.payusdk.utils.Constants.NOT_INITIALIZED;

public class ALURequestBuilder extends BaseRequestBuilder {

    private int purchaseCount;

    public ALURequestBuilder(String secretKey) {
        super(secretKey);
        purchaseCount = NOT_INITIALIZED;
    }

    // -- START required fields ---
    public ALURequestBuilder setMerchantId(String merchant) {
        dataMap.put(MERCHANT, merchant);

        return this;
    }

    public ALURequestBuilder setOrderExternalNumber(String number) {
        dataMap.put(ORDER_REF, number);

        return this;
    }

    public ALURequestBuilder setOrderDate(String date) {
        dataMap.put(ORDER_DATE, date);

        return this;
    }

    public ALURequestBuilder setBackRef(String ref) {
        dataMap.put(BACK_REF, ref);

        return this;
    }

    public ALURequestBuilder setBillClientInfo(ALUBillClientInfo billClientInfo) {
        dataMap.put(BILL_FIRST_NAME, billClientInfo.getFirstName());
        dataMap.put(BILL_LAST_NAME, billClientInfo.getLastName());
        dataMap.put(BILL_EMAIL, billClientInfo.getEmail());
        dataMap.put(BILL_PHONE, billClientInfo.getPhone());
        dataMap.put(BILL_COUNTRY_CODE, billClientInfo.getCountryCode().name());

        if (!TextUtils.isEmpty(billClientInfo.getFax())) {
            dataMap.put(BILL_FAX, billClientInfo.getFax());
        }

        if (!TextUtils.isEmpty(billClientInfo.getAddress())) {
            dataMap.put(BILL_ADDRESS, billClientInfo.getAddress());
        }

        if (!TextUtils.isEmpty(billClientInfo.getAddress2())) {
            dataMap.put(BILL_ADDRESS2, billClientInfo.getAddress2());
        }

        if (!TextUtils.isEmpty(billClientInfo.getZipCode())) {
            dataMap.put(BILL_ZIP_CODE, billClientInfo.getZipCode());
        }

        if (!TextUtils.isEmpty(billClientInfo.getCity())) {
            dataMap.put(BILL_CITY, billClientInfo.getCity());
        }

        if (!TextUtils.isEmpty(billClientInfo.getState())) {
            dataMap.put(BILL_STATE, billClientInfo.getState());
        }

        return this;
    }

    public ALURequestBuilder setCardInfo(ALUCardInfo cardInfo) {
        dataMap.put(CARD_CC_NUMBER, cardInfo.getCcNumber());
        dataMap.put(CARD_EXP_MONTH, cardInfo.getExpMonth());
        dataMap.put(CARD_EXP_YEAR, cardInfo.getExpYear());
        dataMap.put(CARD_CC_CVV, cardInfo.getCcCVV());
        dataMap.put(CARD_CC_OWNER, cardInfo.getCcOwner());

        if (!TextUtils.isEmpty(cardInfo.getCcToken())) {
            dataMap.put(CARD_CC_TOKEN, cardInfo.getCcToken());
        }

        return this;
    }
    // -- END required fields ---

    public ALURequestBuilder setOrderShipping(long shipping) {
        dataMap.put(ORDER_SHIPPING, String.valueOf(shipping));

        return this;
    }

    public ALURequestBuilder setDeliveryData(ALUDeliveryData deliveryData) {
        if (!TextUtils.isEmpty(deliveryData.getFirstName())) {
            dataMap.put(DELIVERY_DATA_FIRST_NAME, deliveryData.getFirstName());
        }

        if (!TextUtils.isEmpty(deliveryData.getLastName())) {
            dataMap.put(DELIVERY_DATA_LAST_NAME, deliveryData.getLastName());
        }

        if (!TextUtils.isEmpty(deliveryData.getEmail())) {
            dataMap.put(DELIVERY_DATA_EMAIL, deliveryData.getEmail());
        }

        if (!TextUtils.isEmpty(deliveryData.getPhone())) {
            dataMap.put(DELIVERY_DATA_PHONE, deliveryData.getPhone());
        }

        if (!TextUtils.isEmpty(deliveryData.getAddress())) {
            dataMap.put(DELIVERY_DATA_ADDRESS, deliveryData.getAddress());
        }

        if (!TextUtils.isEmpty(deliveryData.getZipCode())) {
            dataMap.put(DELIVERY_DATA_ZIP_CODE, deliveryData.getZipCode());
        }

        if (!TextUtils.isEmpty(deliveryData.getCity())) {
            dataMap.put(DELIVERY_DATA_CITY, deliveryData.getCity());
        }

        if (!TextUtils.isEmpty(deliveryData.getState())) {
            dataMap.put(DELIVERY_DATA_STATE, deliveryData.getState());
        }

        if (deliveryData.getCountryCode() != null) {
            dataMap.put(DELIVERY_DATA_COUNTRY_CODE, deliveryData.getCountryCode().name());
        }

        if (!TextUtils.isEmpty(deliveryData.getCompany())) {
            dataMap.put(DELIVERY_DATA_COMPANY, deliveryData.getCompany());
        }

        if (!TextUtils.isEmpty(deliveryData.getAddress2())) {
            dataMap.put(DELIVERY_DATA_ADDRESS2, deliveryData.getAddress2());
        }

        return this;
    }

    public ALURequestBuilder setPaymentMethod(PayMethodType method) {
        dataMap.put(PAY_METHOD, method.name());

        return this;
    }

    public ALURequestBuilder setPriceCurrency(PriceCurrency currency) {
        dataMap.put(PRICES_CURRENCY, currency.name());

        return this;
    }

    public ALURequestBuilder setLanguage(LanguageType language) {
        dataMap.put(LANGUAGE, language.name());

        return this;
    }

    public ALURequestBuilder setClientIp(String ip) {
        dataMap.put(CLIENT_IP, ip);

        return this;
    }

    public ALURequestBuilder setClientTime(String time) {
        dataMap.put(CLIENT_TIME, time);

        return this;
    }

    public ALURequestBuilder setCcNumberTime(String time) {
        dataMap.put(CC_NUMBER_TIME, time);

        return this;
    }

    public ALURequestBuilder setCcOwnerTime(String time) {
        dataMap.put(CC_OWNER_TIME, time);

        return this;
    }

    public ALURequestBuilder setIsTestOrder(boolean enable) {
        dataMap.put(TEST_ORDER, String.valueOf(enable).toUpperCase());

        return this;
    }

    public ALURequestBuilder addProduct(List<ALUProduct> productsList) {
        for (int i = 0; i < productsList.size(); i++) {
            ALUProduct product = productsList.get(i);
            purchaseCount++;

            if (!TextUtils.isEmpty(product.getName())) {
                dataMap.put(String.format(ORDER_PNAME_$, purchaseCount), product.getName());
            }

            if (!TextUtils.isEmpty(product.getCode())) {
                dataMap.put(String.format(ORDER_PCODE_$, purchaseCount), product.getCode());
            }

            if (product.getPrice() != 0) {
                dataMap.put(String.format(ORDER_PRICE_$, purchaseCount), String.valueOf(product.getPrice()));
            }

            if (product.getQuantity() != 0) {
                dataMap.put(String.format(ORDER_QTY_$, purchaseCount), String.valueOf(product.getQuantity()));
            }

            if (!TextUtils.isEmpty(product.getInfo())) {
                dataMap.put(String.format(ORDER_PINFO_$, purchaseCount), product.getInfo());
            }

            if (!TextUtils.isEmpty(product.getVersion())) {
                dataMap.put(String.format(ORDER_VER_$, purchaseCount), product.getVersion());
            }
        }

        return this;
    }

    public Map<String, String> build() {
        if (!dataMap.containsKey(ORDER_DATE)) {
            dataMap.put(ORDER_DATE, DateUtils.getCurrentDateUTC());
        }

        String dataString = createHashDataString();
        dataMap.put(ORDER_HASH, EncodeUtils.encodeString(dataString, secretKey));

        return dataMap;
    }

    public double getPurchasePrice() {
        int sum = 0;

        for (int i = 0; i < purchaseCount + 1; ++i) {
            sum += Double.parseDouble(dataMap.get(String.format(ORDER_PRICE_$, i)));
        }

        return sum;
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

        static final String BACK_REF = "BACK_REF";

        static final String BILL_FAX = "BILL_FAX";
        static final String BILL_ADDRESS = "BILL_ADDRESS";
        static final String BILL_ADDRESS2 = "BILL_ADDRESS2";
        static final String BILL_ZIP_CODE = "BILL_ZIPCODE";
        static final String BILL_CITY = "BILL_CITY";
        static final String BILL_STATE = "BILL_STATE";

        static final String CARD_CC_NUMBER = "CC_NUMBER";
        static final String CARD_EXP_MONTH = "EXP_MONTH";
        static final String CARD_EXP_YEAR = "EXP_YEAR";
        static final String CARD_CC_CVV = "CC_CVV";
        static final String CARD_CC_OWNER = "CC_OWNER";
        static final String CARD_CC_TOKEN = "CC_TOKEN";

        static final String DELIVERY_DATA_FIRST_NAME = "DELIVERY_FNAME";
        static final String DELIVERY_DATA_LAST_NAME = "DELIVERY_LNAME";
        static final String DELIVERY_DATA_EMAIL = "DELIVERY_EMAIL";
        static final String DELIVERY_DATA_PHONE = "DELIVERY_PHONE";
        static final String DELIVERY_DATA_ADDRESS = "DELIVERY_ADDRESS";
        static final String DELIVERY_DATA_ZIP_CODE = "DELIVERY_ZIPCODE";
        static final String DELIVERY_DATA_CITY = "DELIVERY_CITY";
        static final String DELIVERY_DATA_STATE = "DELIVERY_STATE";
        static final String DELIVERY_DATA_COUNTRY_CODE = "DELIVERY_COUNTRYCODE";
        static final String DELIVERY_DATA_COMPANY = "DELIVERY_COMPANY";
        static final String DELIVERY_DATA_ADDRESS2 = "DELIVERY_ADDRESS2";

        static final String CLIENT_IP = "CLIENT_IP";
        static final String CLIENT_TIME = "CLIENT_TIME";

        static final String CC_NUMBER_TIME = "CC_NUMBER_TIME";
        static final String CC_OWNER_TIME = "CC_OWNER_TIME";

        static final String ORDER_PNAME_$ = "ORDER_PNAME[%1$d]";
        static final String ORDER_PCODE_$ = "ORDER_PCODE[%1$d]";
        static final String ORDER_PRICE_$ = "ORDER_PRICE[%1$d]";
        static final String ORDER_QTY_$ = "ORDER_QTY[%1$d]";
        static final String ORDER_PINFO_$ = "ORDER_PINFO[%1$d]";
        static final String ORDER_VER_$ = "ORDER_VER[%1$d]";
    }
}