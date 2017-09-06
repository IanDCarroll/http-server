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

    public static byte[] parse(String request, String serverDirectory) {
        setDirectory(serverDirectory);
        parseHead(request);
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
        FileFridge.deleteBytes(directory, requestedUrl);
        return response;
    }

    public static byte[] head() {
        String byNewlineOrReturn = "\n|\r";
        String fullResponse = new String(get());
        String[] separated = fullResponse.split(byNewlineOrReturn);
        return separated[0].getBytes();
    }

     public static byte[] get() {
        return Chef.plate(directory, requestedUrl, ParamParser.unSmushParams(params));
    }

    public static byte[] post() {
        if (FileFridge.inStock(directory, requestedUrl)) {
            FileFridge.pushBytes(directory, requestedUrl, ParamsChef.plateParams(params));
        }
        return get();
    }

    public static byte[] put() {
        FileFridge.deleteBytes(directory, requestedUrl);
        FileFridge.pushBytes(directory, requestedUrl, ParamsChef.plateParams(params));
        return get();
    }

    public static void resetParser() {
        directory = emptyString;
        requestMethod = emptyString;
        requestedUrl = emptyString;
        params = emptyArray;
        httpVersion = emptyString;
    }
}
