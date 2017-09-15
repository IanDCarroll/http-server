package com.ian;

public class ParamsBodyJoiner {
    public static String[] joinBodyWithParams(String body, String[] params) {
        String[] all = new String[(params.length + 1)];
        all[0] = body;
        for (int i = 1; i < all.length; i++) {
            all[i] = params[i - 1];
        }
        return all;
    }
}
