package com.ian;

public class ParamParser {
    private static final String emptyString = "";
    private static final String paramsDelimiterRegex = "\\?";
    private static final String paramDelimiter = "&";
    private static final String hexDelimiter = "%";
    private static final String assignmentOperator = "=";
    private static final String expandedAssignment = " = ";

    public static String[][] parseUrl(String url) {
        String[] segregatedURL = url.split(paramsDelimiterRegex);
        String[] basicURL = { segregatedURL[0] };
        String rawParams = emptyString;
        try {
            rawParams = segregatedURL[1];
        } catch (ArrayIndexOutOfBoundsException e) {}
        String[] params = parseParams(rawParams);
        String[][] parsedUrl = {basicURL, params};
        return parsedUrl;
    }

    public static String[] parseParams(String params) {
        String[] hexedParams =(params.equals(emptyString)) ? new String[0] :
                params.split(paramDelimiter);
        String[] unHexedParams = new String[hexedParams.length];
        for (int i = 0; i < hexedParams.length; i++) {
            unHexedParams[i] = unHex(hexedParams[i]);
        }
        return unHexedParams;
    }

    public static String unHex(String hexedParams) {
        String[] params = hexedParams.split(emptyString);
        StringBuilder unHexedParams = new StringBuilder();
        String Delimiter = hexDelimiter;
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

    public static String[] unSmushParams(String[] smushedParams) {
        String[] unSmushedParams = new String[smushedParams.length];
        for (int i = 0; i < smushedParams.length; i++) {
            unSmushedParams[i] = unSmushParam(smushedParams[i]);
        }
        return unSmushedParams;
    }

    public static String unSmushParam(String smushedParam) {
        String[] param = smushedParam.split(emptyString);
        StringBuilder unSmushedParam = new StringBuilder();
        boolean foundAssignmentOperator = false;
        for (String letter : param) {
            if (foundAssignmentOperator) {
                unSmushedParam.append(letter);
            } else {
                String unSmushedLetter = unSmushLetter(letter);
                unSmushedParam.append(unSmushedLetter);
                foundAssignmentOperator = isThisIt(unSmushedLetter);
            }
        }
        return unSmushedParam.toString();
    }

    public static boolean isThisIt(String letter) {
        return letter.equals(expandedAssignment);
    }

    public static String unSmushLetter(String letter) {
        return (letter.equals(assignmentOperator)) ? expandedAssignment : letter;
    }
}
