package com.ian;

import java.util.HashMap;

public class StartLineParser {
    private static final String emptyString = "";
    public static HashMap<String, String> parsedStartLine = new HashMap<>();

    public static HashMap<String, String> parseStartLine(String startLine) {
        String byAnyWhiteSpace = "\\s";
        try {
            String[] splitUpRequest = startLine.split(byAnyWhiteSpace);
            try {
                assignStartLine(splitUpRequest[0], splitUpRequest[1], splitUpRequest[2]);
            } catch (ArrayIndexOutOfBoundsException e) { catchStartLineError(); }
        } catch (NullPointerException e) { catchStartLineError(); }
        return parsedStartLine;
    }

    public static void assignStartLine(String method, String urlWithParams, String httpVersion) {
        parsedStartLine.put("method", method);
        parsedStartLine.put("httpVersion", httpVersion);
        assignUrlAndParams(urlWithParams);
    }

    //use the pattern in newParser to eliminate the try-catch
    public static void assignUrlAndParams(String urlWithParams) {
        String byQuestionMark = "\\?";
        String[] splitUpUrl = SplitsParser.bisectString(urlWithParams, byQuestionMark);
        parsedStartLine.put("url", splitUpUrl[0]);
        parsedStartLine.put("urlParams", splitUpUrl[1]);
    }

    public static void catchStartLineError() {
        parsedStartLine.put("method", emptyString);
        parsedStartLine.put("url", emptyString);
        parsedStartLine.put("urlParams", emptyString);
        parsedStartLine.put("httpVersion", emptyString);
    }
}
