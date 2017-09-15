package com.ian;

import java.util.HashMap;

public class Parser {
    private static final String emptyString = "";
    public static HashMap<String, String> parsedRequest = new HashMap<>();

    public static HashMap<String, String> parse(String request) {
        try {
            assignRequest(request);
        } catch (NullPointerException e) { catchNullValue(); }
        return parsedRequest;
    }

    public static void assignRequest(String request) throws NullPointerException {
        String byCRLF = "\r\n\r\n";
        String[] decapitatedRequest = SplitsParser.bisectString(request, byCRLF);
        parseHead(decapitatedRequest[0]);
        parsedRequest.put("body", decapitatedRequest[1]);
    }

    public static void parseHead(String decapitatedHead) {
        String byNewline = "\n";
        String[] flatTop = SplitsParser.bisectString(decapitatedHead, byNewline);
        HashMap<String, String> startLine = StartLineParser.parseStartLine(flatTop[0]);
        parsedRequest.putAll(startLine);
        parsedRequest.put("headers", flatTop[1]);
    }

    public static void catchNullValue() {
        parsedRequest.put("method", emptyString);
        parsedRequest.put("url", emptyString);
        parsedRequest.put("urlParams", emptyString);
        parsedRequest.put("httpVersion", emptyString);
        parsedRequest.put("headers", emptyString);
        parsedRequest.put("body", emptyString);
    }
}
