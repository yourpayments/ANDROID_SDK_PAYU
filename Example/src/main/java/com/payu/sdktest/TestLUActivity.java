package com.payu.sdktest;

import android.app.Activity;
import android.os.Bundle;

import com.payu.payusdk.models.CountryCode;
import com.payu.payusdk.models.PayMethodType;
import com.payu.payusdk.models.PriceCurrency;
import com.payu.payusdk.models.lu.LUProduct;
import com.payu.payusdk.protocols.LURequestBuilder;
import com.payu.payusdk.rest.PAYURestConfig;
import com.payu.payusdk.utils.EncodeUtils;
import com.payu.payusdk.view.LUPurchaseView;

import java.util.ArrayList;
import java.util.List;

public class TestLUActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lu);
        LUPurchaseView luPurchaseView = (LUPurchaseView) findViewById(R.id.web_view);

        List<LUProduct> productsList = new ArrayList<>();

        LUProduct product1 = new LUProduct("some_name1", "some_code1", 123, 1, "19");
        product1.setInfo("some_info1");

        LUProduct product2 = new LUProduct("some_name2", "some_code2", 321, 2, "19");
        product2.setInfo("some_info2");

        productsList.add(product1);
        productsList.add(product2);

        LURequestBuilder builder = new LURequestBuilder("e5|S|X~0@l10_?R4b8|1")
                .setMerchantId("ipolhtst")
                .setOrderExternalNumber("3886786")
                .setOrderDate("2012-04-02")
                .addProducts(productsList)
                .setOrderShipping(0)
                .setPriceCurrency(PriceCurrency.RUB)
                .setOrderTimeout(20000)
                .setBillFirstName("Max")
                .setBillLastName("Mel")
                .setBillCountryCode(CountryCode.RU)
                .setPaymentMethod(PayMethodType.ALFACLICK)
                .setIsTestOrder(true)
                .setDebug(true);

        String data = builder.build();

        byte[] dostDataArray = EncodeUtils.getBytes(data, "UTF-8");
        luPurchaseView.postUrl(PAYURestConfig.SUBMIT_ORDER_LU_URL, dostDataArray);
    }
}