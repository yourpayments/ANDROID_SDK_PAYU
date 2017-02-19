package com.payu.payusdk.utils;

import org.simpleframework.xml.core.Persister;

import java.io.Reader;
import java.io.StringReader;

public class ParseUtils {

    private ParseUtils() {
    }

    public static <T> T parse(Class<? extends T> responseType, String responseString) throws Exception {
        Reader reader = new StringReader(responseString);
        Persister persister = new Persister();
        return persister.read(responseType, reader, false);
    }
}