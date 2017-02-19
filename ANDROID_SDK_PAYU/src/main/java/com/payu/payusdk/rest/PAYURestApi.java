package com.payu.payusdk.rest;

import com.payu.payusdk.models.alu.ALUResponse;
import com.payu.payusdk.models.idn.IDNResponse;
import com.payu.payusdk.models.ios.IOSResponse;
import com.payu.payusdk.models.irn.IRNResponse;

import java.util.Map;

interface PAYURestApi {

    ALUResponse submitOrderALU(Map<String, String> dataMap);

    IOSResponse checkOrderIOS(Map<String, String> dataMap);

    IRNResponse sendRefundNotificationIRN(Map<String, String> dataMap);

    IDNResponse sendDeliveryNotificationIDN(Map<String, String> dataMap);
}