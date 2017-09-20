package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class RedirectListerTest {

    @Test
    public void checkRedirectReturnsTrueIfURLIsToBeRedirected() {
        String url = "/redirect";
        boolean check = RedirectLister.checkRedirect(url);
        assertTrue(check);
    }

    @Test
    public void checkRedirectReturnsFalseIfURLIsNotToBeRedirected() {
        String url = "/do-not-redirect";
        boolean check = RedirectLister.checkRedirect(url);
        assertFalse(check);
    }

    @Test
    public void getRedirectionUrlReturnsTheTemporaryUrl() {
        //GIVEN
        String expected = "/";
        String url = "/redirect";
        //WHEN
        String actual = RedirectLister.getRedirectionUrl(url);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void getRedirectionUrlReturnsTheUrlIfNotFound() {
        String expectedUrl = "/not-in-redirect-list";
        String actual = RedirectLister.getRedirectionUrl(expectedUrl);
        assertEquals(expectedUrl, actual);
    }
}