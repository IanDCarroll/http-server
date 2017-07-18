package com.ian;

public class Chef {
    public static String plate(String order) {
        return (order.equals("/")) ? "200 OK" : "404 Not Found";
    }
}
