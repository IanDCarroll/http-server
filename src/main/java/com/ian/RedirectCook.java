package com.ian;

public class RedirectCook {
    private static String found = "HTTP/1.1 302 Found";

    public static byte[] craft302Response(String request) {
        String response = found + HeadersCook.craftLocation(request);
        return response.getBytes();
    }
}
