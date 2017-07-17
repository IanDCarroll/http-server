package com.ian;

public class Chef {
    public static int plate(String order) {
        return (order.equals("/")) ? 200 : 404;
    }
}
