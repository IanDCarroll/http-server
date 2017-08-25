package com.ian;

import java.util.Arrays;
import java.util.stream.Stream;

public class ParamParser {
    public static String[] parseUrl(String url) {
        String[] segregatedURL = url.split("\\?");

        String[] basicURL = { segregatedURL[0] };
        String[] params = {};
        String[] parsedURL = Stream.concat(Arrays.stream(basicURL), Arrays.stream(params))
                .toArray(String[]::new);
        return parsedURL;
    }

    public static String [] parseParams(String params) {
        String unHexedParams = unHex(params);
        String [] parsedParams = unHexedParams.split("&");
        return parsedParams;
    }

    public static String unHex(String hexedParams) {
        String[] params = hexedParams.split("");
        StringBuilder unHexedParams = new StringBuilder();
        String Delimiter = "%";
        int lookForNextDelimiter = 2;
        int dontLookYet = 0;
        int ignoreCounter = lookForNextDelimiter;
        for (int i = 0; i < params.length; i++) {
            if (params[i].equals(Delimiter)) {
                String hex = params[i + 1] + params[i + 2];
                unHexedParams.append(unHexChar(hex));
                ignoreCounter = dontLookYet;
            } else if (ignoreCounter != lookForNextDelimiter) {
                ignoreCounter ++;
            }else {
                unHexedParams.append(params[i]);
            }
        }
        return unHexedParams.toString();
    }

    public static String unHexChar(String hex) {
        final int base = 16;
        byte hexByte = Byte.parseByte(hex, base);
        return new String(new byte[] {hexByte});
    }
}
