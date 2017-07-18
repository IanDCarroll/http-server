package com.ian;

public class Parser {
    public static String parse(String request) {
        String[] splitReq = request.split(" ");
        return "HTTP/1.1 " + Chef.plate(splitReq[1]);
    }
}
