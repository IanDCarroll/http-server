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

    public static String[] expandAssignmentOperators(String[] unexpandedParams) {
        String[] expandedParams = new String[unexpandedParams.length];
        for (int i = 0; i < unexpandedParams.length; i++) {
            expandedParams[i] = expandOneAssignmentOperator(unexpandedParams[i]);
        }
        return expandedParams;
    }

    public static String expandOneAssignmentOperator(String oneUnexpandedParam) {
        StringBuilder oneExpandedParam = new StringBuilder();
        String[] paramLetters = oneUnexpandedParam.split(emptyString);
        boolean foundAssignmentOperator = false;
        for (String letter : paramLetters) {
            if (foundAssignmentOperator) {
                oneExpandedParam.append(letter);
            } else {
                foundAssignmentOperator = thisIsTheAssignmentOperator(letter);
                oneExpandedParam.append(expandIfAssignmentOperator(letter));
            }
        }
        return oneExpandedParam.toString();
    }

    public static String expandIfAssignmentOperator(String letter) {
        return (thisIsTheAssignmentOperator(letter)) ? expandedAssignment : letter;
    }

    public static boolean thisIsTheAssignmentOperator(String letter) {
        return letter.equals(assignmentOperator);
    }
}
