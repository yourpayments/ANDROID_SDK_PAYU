package com.payu.payusdk.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_TIMEZONE = "UTC";

    private DateUtils() {
    }

    public static String getCurrentDateUTC() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        return simpleDateFormat.format(new Date());
    }
}