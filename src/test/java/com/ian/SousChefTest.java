package com.ian;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class SousChefTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void sousChefTypeReturnsContentType() {
        File file = new File(directory, "file1");
        String expected = "\nContent-Length: 14" +
                          "\nContent-Type: text/plain";
        assertEquals(expected, SousChef.plateHeaders(file));
    }

    @Test
    public void plateContentTypeReturnsContentTypeTextPlainAsDefault() {
        String expected = "\nContent-Type: text/plain";
        assertEquals(expected, SousChef.plateContentType("file1"));
    }

    @Test
    public void plateContentTypeReturnscontentTypeImageGifWhenFileIsAGif() {
        String expected = "\nContent-Type: image/gif";
        assertEquals(expected, SousChef.plateContentType("file1.gif"));
    }

    @Test
    public void plateContentLengthReturnsContentLength() {
        String expected = "\nContent-Length: 42";
        assertEquals(expected, SousChef.plateContentLength(42));
    }
}