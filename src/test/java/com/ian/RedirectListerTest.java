package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class RedirectListerTest {

    @Test
    public void checkRedirectReturnsTrueIfURLIsToBeRedirected() {
        String url = "/redirect";
        boolean expected = true;
        boolean actual = RedirectLister.checkRedirect(url);
        assertEquals(expected, actual);
    }

    @Test
    public void checkRedirectReturnsFalseIfURLIsNotToBeRedirected() {
        String url = "/do-not-redirect";
        boolean expected = false;
        boolean actual = RedirectLister.checkRedirect(url);
        assertEquals(expected, actual);
    }

    @Test
    public void getRedirectionUrlReturnsTheTemporaryUrl() {
        String url = "/redirect";
        String expected = "/";
        String actual = RedirectLister.getRedirectionUrl(url);
        assertEquals(expected, actual);
    }

    @Test
    public void getRedirectionUrlReturnsTheUrlIfNotFound() {
        String url = "/not-in-redirect-list";
        String actual = RedirectLister.getRedirectionUrl(url);
        assertEquals(url, actual);
    }
}