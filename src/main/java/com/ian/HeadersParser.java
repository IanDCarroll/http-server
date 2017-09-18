package com.ian;

import java.util.HashMap;

public class HeadersParser {
    public static HashMap<String, String> parseHeaders(String headers) {
        return mapAll(separate(headers));
    }

    public static String[] separate(String headers) {
        String byNewLine = "\n";
        return headers.split(byNewLine);
    }

    public static HashMap<String, String> mapAll(String[] separatedHeaders) {
        HashMap<String, String> mappedHeaders = new HashMap<>();
        for (String header : separatedHeaders) {
            String[] parsedHeader = parseOne(header);
            mappedHeaders.put(parsedHeader[0], parsedHeader[1]);
        }
        return mappedHeaders;
    }

    public static String[] parseOne(String header) {
        String byColonAndSpace = ": ";
        return header.split(byColonAndSpace);
    }
}
