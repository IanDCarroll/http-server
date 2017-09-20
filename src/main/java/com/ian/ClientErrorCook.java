package com.ian;

public class ClientErrorCook {
    private static final String HTTPVersion = "HTTP/1.1 ";
    private static final String badRequest = "400 Bad Request";
    private static final String unauthorized = "401 Unauthorized";
    private static final String notFound = "404 Not Found";
    private static final String rangeNotSatisfiable = "416 Range Not Satisfiable";
    private static final String teaPot = "418 I'm a teapot.";

    public static byte[] craft400Response() { return addHTTPto(badRequest).getBytes(); }

    public static byte[] craft401Response() { return addHTTPto(unauthorized).getBytes(); }

    public static byte[] craft404Response() {
        return addHTTPto(notFound).getBytes();
    }

    public static byte[] craft416Response() {
        return addHTTPto(rangeNotSatisfiable).getBytes();
    }

    public static byte[] craft418Response() {
        return addHTTPto(teaPot).getBytes();
    }

    public static String addHTTPto(String code) {
        return HTTPVersion + code;
    }
}
