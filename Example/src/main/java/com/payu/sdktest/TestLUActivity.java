package com.payu.sdktest;

import android.app.Activity;
import android.os.Bundle;

import com.payu.payusdk.models.CountryCode;
import com.payu.payusdk.models.LanguageType;
import com.payu.payusdk.models.PayMethodType;
import com.payu.payusdk.models.PriceCurrency;
import com.payu.payusdk.protocols.LURequestBuilder;
import com.payu.payusdk.rest.PAYURestConfig;
import com.payu.payusdk.utils.EncodeUtils;
import com.payu.payusdk.view.LUPurchaseView;

public class TestLUActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lu);
        LUPurchaseView LUPurchaseView = (LUPurchaseView) findViewById(R.id.web_view);

        LURequestBuilder builder = new LURequestBuilder("e5|S|X~0@l10_?R4b8|1")
                .setMerchantId("ipolhtst")
                .setOrderExternalNumber("3886786")
                .setBillFirstName("Max")
                .setBillLastName("Mel")
                .setBillCountryCode(CountryCode.RU)
                .setLanguage(LanguageType.RU)
                .setDiscount(200.1)
                .setPaymentMethod(PayMethodType.CCVISAMC)
                .setOrderShipping(1200)
                .setPriceCurrency(PriceCurrency.USD)
                .setOrderTimeout(20000)
                .setIsTestOrder(true)
                .setDebug(true);

        LUPurchaseView.postUrl(PAYURestConfig.SUBMIT_ORDER_LU_URL, EncodeUtils.getBytes(builder.build(), "BASE64"));
    }
}