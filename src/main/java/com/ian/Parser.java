package com.ian;

public class Parser {
    private static final String DELETE = "DELETE";
    private static final String GET = "GET";
    private static final String HEAD = "HEAD";
    private static final String POST = "POST";
    private static final String PUT = "PUT";
    public static String directory = "";
    public static String requestMethod = "";
    public static String requestedURI = "";
    public static String httpVersion = "";
    public static String requestBody = "";

    public static byte[] parse(String request, String serverDirectory) {
        setDirectory(serverDirectory);
        parseHead(request);
        setRequestBody(request);
        return illFormedRequest() ? null : applyAppropriateMethod();
    }

    public static void setDirectory(String serverDirectory) {
        directory = serverDirectory;
    }

    public static void parseHead(String request) {
        String byAnyWhiteSpace = "\\s";
        try {
            String[] splitReq = request.split(byAnyWhiteSpace);
            requestMethod = splitReq[0];
            requestedURI = splitReq[1];
            httpVersion = splitReq[2];
        } catch (NullPointerException e) {
            resetParser();
        }
    }

    public static void setRequestBody(String request) {
        String byCRLF = "\r\n\r\n";
        try {
            String[] decaptitated = request.split(byCRLF);
            try {
                String body = decaptitated[1];
                for (int i = 2; i < (decaptitated.length); i++) {
                    body = String.join(byCRLF, body, decaptitated[i]);
                }
                requestBody = body;
            } catch (ArrayIndexOutOfBoundsException e) {}
        } catch (NullPointerException e) {}
    }

    public static boolean illFormedRequest() {
        return (requestMethod.isEmpty() || requestedURI.isEmpty() || httpVersion.isEmpty());
    }

    public static byte[] applyAppropriateMethod() {
        byte [] response = null;
        if(requestMethod.equals(DELETE)) {
            response = delete();
        } else if (requestMethod.equals(GET)) {
            response = get();
        } else if (requestMethod.equals(HEAD)) {
            response = head();
        } else if (requestMethod.equals(POST)) {
            response = post();
        } else if (requestMethod.equals(PUT)) {
            response = put();
        } else { resetParser(); }
        return response;
    }

    public static byte[] delete() {
        //make a name from the requestedURI
        //delete any file that has that name
        return get();
    }

    public static byte[] head() {
        String byNewlineOrReturn = "\n|\r";
        String fullResponse = new String(get());
        String[] separated = fullResponse.split(byNewlineOrReturn);
        return separated[0].getBytes();
    }

     public static byte[] get() {
        return Chef.plate(directory, ParamParser.parseUrl(requestedURI));
    }

    public static byte[] post() {
        //get what's in the request body -DONE
        //write it to the requestedURI file
        return get();
    }

    public static byte[] put() {
        // get what's in the request body -DONE
        // make a name from the requestedURI
        // create or overwrite a file with that name
        return get();
    }

    public static void resetParser() {
        directory = "";
        requestMethod = "";
        requestedURI = "";
        httpVersion = "";
    }
}
