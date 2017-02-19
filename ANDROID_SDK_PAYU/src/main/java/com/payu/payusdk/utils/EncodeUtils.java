package com.payu.payusdk.utils;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class EncodeUtils {

    private static final String ENCODING_TYPE = "HmacMD5";

    private EncodeUtils() {
    }

    public static String encodeString(String data, String secretKey) {
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), ENCODING_TYPE);

        Mac mac = null;
        try {
            mac = Mac.getInstance(ENCODING_TYPE);
            mac.init(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            LogsUtils.logE(e);
        }

        byte[] result = new byte[0];
        if (mac != null) {
            result = mac.doFinal(data.getBytes());
        }

        StringBuilder hexStringBuilder = new StringBuilder();
        for (int i = 0; i < result.length; ++i) {
            hexStringBuilder.append(Integer.toHexString(0xFF & result[i] | 0x100).substring(1, 3));
        }

        return hexStringBuilder.toString();
    }

    public static byte[] getBytes(String data, String charset) {
        if (TextUtils.isEmpty(data) || TextUtils.isEmpty(charset)) {
            return new byte[0];
        }

        try {
            return data.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            LogsUtils.logE(e);
        }

        return data.getBytes();
    }
}