package com.ian;

import java.util.Base64;
import java.util.HashMap;

public class AuthValidator {
    private static HashMap<String, String> restricted = new HashMap<>();
    static { restricted.put("/logs","admin:hunter2"); }

    public static boolean unauthorized(String url, String unParsedHeaders) {
        if (authorizationRequired(url)) {
            String auth = b64encoded(restricted.get(url));
            return !unParsedHeaders.contains("Authorization: Basic " + auth);
        }
        return false;
    }

    public static boolean authorizationRequired(String url) {
        for (String resource : restricted.keySet()) {
            if (url.equals(resource)) { return true; }
        }
        return false;
    }

    public static String b64encoded(String authorization) {
        return Base64.getEncoder().withoutPadding().encodeToString(authorization.getBytes());
    }
}
