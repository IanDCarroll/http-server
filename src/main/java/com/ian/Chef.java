package com.ian;

public class Chef {
    public static final String crlf = "\r\n\r\n";
    public static final String ok = "HTTP/1.1 200 OK" + crlf;
    public static final String notFound = "HTTP/1.1 404 Not Found";

    public static String plate(String order) {
        return (order.equals("/")) ? ok : searchMenu(order);
    }

    public static String searchMenu(String uri) {
        //access the file requested
        if (uri.equals("/file1"))
            //concatenate it to the response line
            return ok + "file1 contents";
        else
            return notFound;
    }
}
