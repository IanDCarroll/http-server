package com.ian;

public class ParamsParser {
    private static final String emptyString = "";
    private static final String[] emptyArray = {};
    private static final String byAmpersand = "&";

    public static String[] parseParams(String params) {
        if (params.equals(emptyString)) { return emptyArray; }
        String[] hexedParams = params.split(byAmpersand);
        String[] unHexedParams = new String[hexedParams.length];
        for (int i = 0; i < hexedParams.length; i++) {
            unHexedParams[i] = HexParser.unHex(hexedParams[i]);
        }
        return unHexedParams;
    }
}
