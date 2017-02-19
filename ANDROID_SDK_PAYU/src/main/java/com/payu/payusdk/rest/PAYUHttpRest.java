package com.payu.payusdk.rest;

import android.text.TextUtils;
import android.util.Pair;

import com.payu.payusdk.models.alu.ALUResponse;
import com.payu.payusdk.models.idn.IDNResponse;
import com.payu.payusdk.models.ios.IOSResponse;
import com.payu.payusdk.models.irn.IRNResponse;
import com.payu.payusdk.utils.LogsUtils;
import com.payu.payusdk.utils.ParseUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import static com.payu.payusdk.utils.Constants.ENCODING;

public class PAYUHttpRest implements PAYURestApi {

    @Override
    public ALUResponse submitOrderALU(Map<String, String> dataMap) {
        return makeRequest(ALUResponse.class,
                PAYURestConfig.SUBMIT_ORDER_ALU_URL, RestMethod.POST, dataMap);
    }

    @Override
    public IOSResponse checkOrderIOS(Map<String, String> dataMap) {
        return makeRequest(IOSResponse.class,
                PAYURestConfig.CHECK_ORDER_IOS_URL, RestMethod.POST, dataMap);
    }

    @Override
    public IRNResponse sendRefundNotificationIRN(Map<String, String> dataMap) {
        return makeRequest(IRNResponse.class,
                PAYURestConfig.SEND_REFUND_NOTIFICATION_IRN_URL, RestMethod.POST, dataMap);
    }

    @Override
    public IDNResponse sendDeliveryNotificationIDN(Map<String, String> dataMap) {
        return makeRequest(IDNResponse.class,
                PAYURestConfig.SEND_DELIVERY_NOTIFICATION_IDN_URL, RestMethod.POST, dataMap);
    }

    private <T> T makeRequest(Class<? extends T> responseType, String url, RestMethod restMethod, Map<String, String> dataMap) {
        String responseString = makeRequest(url, restMethod, dataMap);

        if (!TextUtils.isEmpty(responseString)) {
            try {
                return ParseUtils.parse(responseType, responseString);
            } catch (Exception e) {
                LogsUtils.logE(e);
            }
        }

        return null;
    }

    private String makeRequest(String url, RestMethod restMethod, Map<String, String> dataMap) {
        String result = "";

        HttpURLConnection httpURLConnection;

        OutputStream outputStream = null;
        BufferedWriter bufferedWriter = null;

        LogsUtils.logD("---> START REQUEST ---");
        LogsUtils.logD("--- URL: " + url);

        try {
            httpURLConnection = (HttpURLConnection) ((new URL(url).openConnection()));
            httpURLConnection.setRequestMethod(restMethod.name());
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            List<Pair> paramsList = new ArrayList<>();
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                paramsList.add(new Pair(entry.getKey(), entry.getValue()));
            }

            outputStream = httpURLConnection.getOutputStream();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, ENCODING));
            bufferedWriter.write(getQuery(paramsList));
            bufferedWriter.flush();

            httpURLConnection.connect();

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));

                LogsUtils.logD("--- RESPONSE:");

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                    LogsUtils.logD(line);
                }
            }
        } catch (Exception e) {
            LogsUtils.logE(e);
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                LogsUtils.logE(e);
            }
        }

        LogsUtils.logD("<--- END REQUEST ---");

        return result;
    }

    private String getQuery(List<Pair> paramsList) throws UnsupportedEncodingException {
        StringBuilder resultStringBuilder = new StringBuilder();
        boolean first = true;

        for (Pair pair : paramsList) {
            if (first) {
                first = false;
            } else {
                resultStringBuilder.append("&");
            }

            String key = URLEncoder.encode(pair.first.toString(), ENCODING);
            String value = URLEncoder.encode(pair.second.toString(), ENCODING);

            resultStringBuilder.append(key);
            resultStringBuilder.append("=");
            resultStringBuilder.append(value);
        }

        String resultString = resultStringBuilder.toString();
        LogsUtils.logD("--- DATA: " + resultString);

        return resultString;
    }
}