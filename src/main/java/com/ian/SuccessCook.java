package com.ian;

public class SuccessCook {
    public static byte[] craft200Response() {
        return "HTTP/1.1 200 OK".getBytes();
    }

    public static byte[] craft206Response() {
        return "HTTP/1.1 206 Partial Content".getBytes();
    }
}
