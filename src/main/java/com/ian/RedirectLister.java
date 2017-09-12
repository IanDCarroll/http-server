package com.ian;

import java.util.stream.Stream;

public class RedirectLister {
    private static String[] redirectFrom = { "/redirect" };
    private static String[] redirectTo = { "/" };

    public static boolean checkRedirect(String url) {
        return Stream.of(redirectFrom).anyMatch(x -> x.equals(url));
    }

    public static String getRedirectionUrl(String url) {
        String redirection = url;
        for (int i = 0; i < redirectFrom.length; i++) {
            if (redirectFrom[i].equals(url)) { redirection = redirectTo[i]; }
        }
        return redirection;
    }
}
