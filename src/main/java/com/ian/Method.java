package com.ian;

public class Method {
    public static byte[] delete(String directory, String url) {
        byte[] response = Kitchen.sendOrderToChef(directory, url);
        FileStocker.deleteBytes(directory, url);
        return response;
    }

    public static byte[] head(String directory, String url) {
        return Kitchen.sendOrderToSousChef(directory, url);
    }

    public static byte[] get(String directory, String url, String[] params) {
        return Kitchen.sendOrderToChef(directory, url, params);
    }

    public static byte[] post(String directory, String url, String body, String[] params) {
        if (FileStocker.inStock(directory, url)) {
            FileStocker.pushBytes(directory, url, jointData(body, params));
        }
        return Kitchen.sendOrderToChef(directory, url);
    }

    public static byte[] put(String directory, String url, String body, String[] params) {
        FileStocker.deleteBytes(directory, url);
        FileStocker.pushBytes(directory, url, jointData(body, params));
        return Kitchen.sendOrderToChef(directory, url);
    }

    private static byte[] jointData(String body, String[] params) {
        return ParamsCook.craftParams(ParamsBodyJoiner.joinBodyWithParams(body, params));
    }
}
