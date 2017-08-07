package com.ian;

public class Parser {
    public static String requestedURI;

    public static String parse(String request, String directory) {
        String[] splitReq = request.split("\\s");
        requestedURI = splitReq[1];
        return Chef.plate(directory, requestedURI);
    }
}
