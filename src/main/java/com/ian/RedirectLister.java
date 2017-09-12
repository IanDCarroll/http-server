package com.ian;

import java.util.HashMap;

public class RedirectLister {
    private static HashMap<String, String> redirectMap;
    static {
        redirectMap = new HashMap<>();
        redirectMap.put("/redirect", "/");
    }

    public static boolean checkRedirect(String url) {
        for (String key: redirectMap.keySet()) {
            if (key.equals(url)) { return true; }
        }
        return false;
    }

    public static String getRedirectionUrl(String url) {
        for (String key: redirectMap.keySet()) {
            if (key.equals(url)) { return redirectMap.get(key); }
        }
        return url;
    }
}
