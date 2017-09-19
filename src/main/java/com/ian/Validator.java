package com.ian;

import java.util.Base64;
import java.util.HashMap;

public class Validator {
    private static HashMap<String, String> restricted = new HashMap<>();
    static { restricted.put("/logs","admin:hunter2"); }

    public static byte[] validate(String directory, String request) {
        HashMap<String, String> parsed = Parser.parse(request);
        if (badRequest(parsed.get("method"),
                       parsed.get("url"),
                       parsed.get("httpVersion"))) {
            return DisembodiedSousChef.craft400Response();
        } else if (unauthorized(parsed.get("url"),
                                parsed.get("headers"))) {
            return DisembodiedSousChef.craft401Response();
        }
        return Methodizer.takeOrder(directory, request);
    }

    public static boolean badRequest(String method, String url, String httpVersion) {
        return method.isEmpty() || url.isEmpty() || httpVersion.isEmpty();
    }

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

    public static boolean notAllowed() { return false; }

    public static boolean notInRange() { return false; }
}
