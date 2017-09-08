package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class HeadersCookTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void headersChefTypeReturnsContentType() {
        String expected = "\nContent-Length: 14" +
                           "\nContent-Type: text/plain";
        String actual = new String(HeadersCook.craftHeaders(directory, "/file1"));
        assertEquals(expected, actual);
    }

    @Test
    public void plateContentTypeReturnsContentTypeTextPlainAsDefault() {
        String expected = "\nContent-Type: text/plain";
        assertEquals(expected, HeadersCook.craftContentType("text/plain"));
    }

    @Test
    public void plateContentLengthReturnsContentLength() {
        long length = FileStocker.size(directory, "/file1");
        String expected = "\nContent-Length: 14";
        assertEquals(expected, HeadersCook.craftContentLength(length));
    }
}