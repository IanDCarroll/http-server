package com.ian;

public class Parser {
    public static String requestedURI;

    public static byte[] parse(String request, String directory) {
        try {
            String[] splitReq = request.split("\\s");
            requestedURI = splitReq[1];
            return Chef.plate(directory, ParamParser.parseUrl(requestedURI));
        } catch ( NullPointerException e ) {
            requestedURI = null;
            return null;
        }
    }
}
