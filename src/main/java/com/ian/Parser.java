package com.ian;

public class Parser {
    private static final String DELETE = "DELETE";
    private static final String GET = "GET";
    private static final String HEAD = "HEAD";
    private static final String POST = "POST";
    private static final String PUT = "PUT";
    private static final String emptyString = "";
    private static final String[] emptyArray = {};
    public static String directory = emptyString;
    public static String requestMethod = emptyString;
    public static String requestedUrl = emptyString;
    public static String[] params = emptyArray;
    public static String httpVersion = emptyString;
    public static String requestBody = emptyString;

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
            try {
                requestMethod = splitReq[0];
                String[][] parsedUrl = ParamParser.parseUrl(splitReq[1]);
                requestedUrl = parsedUrl[0][0];
                params = parsedUrl[1];
                httpVersion = splitReq[2];
            } catch (ArrayIndexOutOfBoundsException e) { System.out.println("ArrayIndexOutOfBounds in Parser.parseHead"); }
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
        return (requestMethod.isEmpty() || requestedUrl.isEmpty() || httpVersion.isEmpty());
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
        }
        resetParser();
        return response;
    }

    public static byte[] delete() {
        byte[] response = get();
        FileStocker.deleteBytes(directory, requestedUrl);
        return response;
    }

    public static byte[] head() {
        String byNewlineOrReturn = "\n|\r";
        String fullResponse = new String(get());
        String[] separated = fullResponse.split(byNewlineOrReturn);
        return separated[0].getBytes();
    }

     public static byte[] get() {
        return ResponseChef.craftResponse(directory, requestedUrl, ParamParser.expandAssignmentOperators(params));
    }

    public static byte[] post() {
        if (FileStocker.inStock(directory, requestedUrl)) {
            FileStocker.pushBytes(directory, requestedUrl, ParamsCook.craftParams(addAllParams()));
        }
        return get();
    }

    public static byte[] put() {
        FileStocker.deleteBytes(directory, requestedUrl);
        FileStocker.pushBytes(directory, requestedUrl, ParamsCook.craftParams(addAllParams()));
        return get();
    }

    public static String[] addAllParams() {
        String[] all = new String[(params.length + 1)];
        all[0] = requestBody;
        for (int i = 1; i < all.length; i++) {
            all[i] = params[i-1];
        }
        return all;
    }

    public static void resetParser() {
        directory = emptyString;
        requestMethod = emptyString;
        requestedUrl = emptyString;
        params = emptyArray;
        httpVersion = emptyString;
        requestBody = emptyString;
    }
}
