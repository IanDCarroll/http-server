package com.ian;

public class Chef {
    public static String plate(String order) {
        return (order.equals("/")) ? "HTTP/1.1 200 OK" : searchMenu(order);
    }

    public static String searchMenu(String uri) {
        if (uri.equals("/file1"))
            return "HTTP/1.1 200 OK\r\n\r\nfile1 contents";
        else
            return "HTTP/1.1 404 Not Found";
    }
}
