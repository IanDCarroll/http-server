package com.ian;

public class HTCPCPChecker {
    public static boolean check(String request) {
        return checkCoffee(request) || checkTea(request);
    }

    public static boolean checkCoffee(String request) {
        return request.equals("/coffee");
    }

    public static boolean checkTea(String request) {
        return request.equals("/tea");
    }
}
