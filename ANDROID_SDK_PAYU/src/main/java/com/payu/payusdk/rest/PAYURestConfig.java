package com.payu.payusdk.rest;

public class PAYURestConfig {

    private static final String BASE_REST_API = "https://secure.ypmn.ru";

    static final String SUBMIT_ORDER_ALU_URL = BASE_REST_API.concat("/order/alu/v3");
    static final String CHECK_ORDER_IOS_URL = BASE_REST_API.concat("/order/ios.php");
    static final String SEND_REFUND_NOTIFICATION_IRN_URL = BASE_REST_API.concat("/order/irn.php");
    static final String SEND_DELIVERY_NOTIFICATION_IDN_URL = BASE_REST_API.concat("/order/idn.php");

    public static final String SUBMIT_ORDER_LU_URL = BASE_REST_API.concat("/order/lu.php");
}