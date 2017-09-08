package com.ian;

public class ClientErrorCook {
    public static byte[] craft4XXResponse() {
        return "HTTP/1.1 404 Not Found".getBytes();
    }

}
