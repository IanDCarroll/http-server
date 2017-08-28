package com.ian;

import java.util.Arrays;

public class ParamsChef {
    public static byte[] plateParams(String[] parsedURL) {
        int ignoreURLPath = 1;
        String[] parsedParams = Arrays.copyOfRange(parsedURL, ignoreURLPath, parsedURL.length);
        return sauteParams(parsedParams);
    }

    public static byte[] sauteParams(String[] parsedParams) {
        String delimiter = "\n";
        return String.join(delimiter, parsedParams).getBytes();
    }
}
