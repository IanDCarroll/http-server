package com.ian;

public class Kitchen {
    public static byte[] sendOrderToChef(String directory, String url) {
        String[] withEmptyParams = {};
        return sendOrderToChef(directory, url, withEmptyParams);
    }

    public static byte[] sendOrderToChef(String directory, String url, String[] params) {
        return ResponseChef.craftResponse(directory, url, params);
    }

    public static byte[] sendOrderToSousChef(String directory, String url) {
        byte[] emptyBody = "".getBytes();
        return DisembodiedSousChef.craftResponseHead(directory, url, emptyBody);
    }
}
