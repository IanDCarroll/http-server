package com.ian;

public class Parser {
    public static String parse(String request) {
        String[] splitReq = request.split("\\s");
        String requestedURI = splitReq[1];
        return Chef.plate(requestedURI);
    }
}
