package com.payu.sdktest;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.payu.payusdk.models.CountryCode;
import com.payu.payusdk.models.LanguageType;
import com.payu.payusdk.models.PayMethodType;
import com.payu.payusdk.models.PriceCurrency;
import com.payu.payusdk.models.alu.ALUBillClientInfo;
import com.payu.payusdk.models.alu.ALUCardInfo;
import com.payu.payusdk.models.alu.ALUProduct;
import com.payu.payusdk.models.alu.ALUResponse;
import com.payu.payusdk.models.idn.IDNResponse;
import com.payu.payusdk.models.ios.IOSResponse;
import com.payu.payusdk.models.irn.IRNResponse;
import com.payu.payusdk.protocols.ALURequestBuilder;
import com.payu.payusdk.protocols.IDNRequestBuilder;
import com.payu.payusdk.protocols.IOSRequestBuilder;
import com.payu.payusdk.protocols.IRNRequestBuilder;
import com.payu.payusdk.rest.PAYUHttpRest;

import java.util.ArrayList;
import java.util.List;

public class TestMainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] actionsArray = getResources().getStringArray(R.array.test_actions_array);

        getListView().setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, actionsArray));
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(TestMainActivity.this, TestLUActivity.class));
                        break;
                    case 1:
                        makeTestRequestALU();
                        break;
                    case 2:
                        makeTestRequestIOS();
                        break;
                    case 3:
                        makeTestRequestIRN();
                        break;
                    case 4:
                        makeTestRequestIDN();
                        break;
                }
            }
        });
    }

    private void makeTestRequestALU() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ALUBillClientInfo billClientInfo = new ALUBillClientInfo(
                        "Mel",
                        "Maxim",
                        "email1@email1.com",
                        "70000000000",
                        CountryCode.RU);

                ALUCardInfo cardInfo = new ALUCardInfo("0111 1111 1111 1111", "01", "2017", "071", "Maxim");

                List<ALUProduct> productsList = new ArrayList<>();
                productsList.add(new ALUProduct("name1", "code1", 1, 1, "info1", "version1"));
                productsList.add(new ALUProduct("name2", "code2", 2, 2, "info2", "version2"));

                ALURequestBuilder requestBuilder = new ALURequestBuilder("e5|S|X~0@l10_?R4b8|1")
                        .setMerchantId("ipolhtst")
                        .setOrderExternalNumber("3886786")
                        .setBackRef("")
                        .setBillClientInfo(billClientInfo)
                        .setPriceCurrency(PriceCurrency.RUB)
                        .setPaymentMethod(PayMethodType.CCVISAMC)
                        .setLanguage(LanguageType.RU)
                        .setOrderShipping(1007)
                        .setCardInfo(cardInfo)
                        .addProduct(productsList)
                        .setIsTestOrder(true);

                final ALUResponse response = new PAYUHttpRest().submitOrderALU(requestBuilder.build());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showAlertDialog(response.getStatus(), response.getMessage());
                    }
                });
            }
        }).start();
    }

    private void makeTestRequestIOS() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                IOSRequestBuilder requestBuilder = new IOSRequestBuilder("e5|S|X~0@l10_?R4b8|1")
                        .setMerchantId("ipolhtst")
                        .setRefnoext("3886786");

                final IOSResponse response = new PAYUHttpRest().checkOrderIOS(requestBuilder.build());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showAlertDialog(response.getOrderDate(), response.getOrderStatus());
                    }
                });
            }
        }).start();
    }

    private void makeTestRequestIRN() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                IRNRequestBuilder requestBuilder = new IRNRequestBuilder("e5|S|X~0@l10_?R4b8|1")
                        .setMerchantId("ipolhtst")
                        .setIrnDate("2017-01-01 01:01:01")
                        .setOrderExternalNumber("3886786")
                        .setOrderAmount("111")
                        .setOrderCurrency(PriceCurrency.RUB);

                final IRNResponse response = new PAYUHttpRest().sendRefundNotificationIRN(requestBuilder.build());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response != null) {
                            showAlertDialog(null, response.getResponseMessage());
                        }
                    }
                });
            }
        }).start();
    }

    private void makeTestRequestIDN() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                IDNRequestBuilder requestBuilder = new IDNRequestBuilder("e5|S|X~0@l10_?R4b8|1")
                        .setMerchantId("ipolhtst")
                        .setIdnDate("2017-01-01 01:01:01")
                        .setOrderExternalNumber("3886786")
                        .setOrderAmount("222")
                        .setOrderCurrency(PriceCurrency.RUB);

                final IDNResponse response = new PAYUHttpRest().sendDeliveryNotificationIDN(requestBuilder.build());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response != null) {
                            showAlertDialog(null, response.getResponseMessage());
                        }
                    }
                });
            }
        }).start();
    }

    private void showAlertDialog(String status, String responseMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(TestMainActivity.this)
                .setTitle(status)
                .setMessage(responseMessage).setCancelable(false)
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}