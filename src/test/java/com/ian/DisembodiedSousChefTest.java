package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class DisembodiedSousChefTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void craftResponseHeadReturnsAResponseHead() {
        byte[] expected = ("HTTP/1.1 200 OK" +
                "\nContent-Length: 14" +
                "\nContent-Type: text/plain" +
                "\r\n\r\n").getBytes();
        String request = "/file1";
        byte[] actual = DisembodiedSousChef.craftResponseHead(directory, request);
        assertEquals(new String(expected), new String(actual));
    }
}