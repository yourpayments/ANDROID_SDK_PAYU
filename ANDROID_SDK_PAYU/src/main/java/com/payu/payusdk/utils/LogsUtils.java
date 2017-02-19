package com.payu.payusdk.utils;

import android.util.Log;

public class LogsUtils {

    private final static String TAG = "PayUAndroidSdk";

    public static void logD(String text) {
        Log.d(TAG, text);
    }

    public static void logI(String text) {
        Log.i(TAG, text);
    }

    public static void logE(String text) {
        Log.e(TAG, text);
    }

    public static void logE(Throwable e) {
        Log.e(TAG, e.getMessage());
        e.printStackTrace();
    }

    public static void logW(String text) {
        Log.w(TAG, text);
    }
}