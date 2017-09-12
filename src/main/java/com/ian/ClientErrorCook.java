package com.ian;

public class ClientErrorCook {
    private static String HTTPVersion = "HTTP/1.1 ";
    private static String notFound = "404 Not Found";
    private static String teaPot = "418 I'm a teapot.";

    public static byte[] craft404Response() {
        return addHTTPto(notFound).getBytes();
    }

    public static byte[] craft418Response() {
        return addHTTPto(teaPot).getBytes();
    }

    public static String addHTTPto(String code) {
        return HTTPVersion + code;
    }
}
