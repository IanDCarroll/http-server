package com.ian;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class HeadersChefTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void headersChefTypeReturnsContentType() {
        String expected = "\nContent-Length: 14" +
                           "\nContent-Type: text/plain";
        String actual = new String(HeadersChef.plateHeaders(directory, "/file1"));
        assertEquals(expected, actual);
    }

    @Test
    public void plateContentTypeReturnsContentTypeTextPlainAsDefault() {
        String expected = "\nContent-Type: text/plain";
        assertEquals(expected, HeadersChef.plateContentType("text/plain"));
    }

    @Test
    public void plateContentLengthReturnsContentLength() {
        long length = FileFridge.size(directory, "/file1");
        String expected = "\nContent-Length: 14";
        assertEquals(expected, HeadersChef.plateContentLength(length));
    }
}