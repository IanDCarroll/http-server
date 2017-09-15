package com.ian;

public class HexParser {
    private static final String emptyString = "";
    private static final String hexDelimiter = "%";

    public static String unHex(String hexedParams) {
        String[] params = hexedParams.split(emptyString);
        StringBuilder unHexedParams = new StringBuilder();
        int lookForNextDelimiter = 2;
        int dontLookYet = 0;
        int ignoreCounter = lookForNextDelimiter;
        for (int i = 0; i < params.length; i++) {
            if (params[i].equals(hexDelimiter)) {
                String hex = params[i + 1] + params[i + 2];
                unHexedParams.append(unHexChar(hex));
                ignoreCounter = dontLookYet;
            } else if (ignoreCounter != lookForNextDelimiter) {
                ignoreCounter ++;
            } else {
                unHexedParams.append(params[i]);
            }
        }
        return unHexedParams.toString();
    }

    public static String unHexChar(String hex) {
        final int base = 16;
        byte [] hexByte = { Byte.parseByte(hex, base) };
        return new String(hexByte);
    }
}
