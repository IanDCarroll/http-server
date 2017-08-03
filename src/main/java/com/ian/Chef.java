package com.ian;

public class Chef {
    public static String plate(String order) {
        return (order.equals("/")) ? "HTTP/1.1 200 OK" : "HTTP/1.1 404 Not Found";
    }
}
