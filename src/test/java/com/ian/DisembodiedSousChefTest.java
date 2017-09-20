package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class DisembodiedSousChefTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void craftResponseHeadReturnsA200ResponseHeadWhenRequestFound() {
        byte[] body = "file1 contents".getBytes();
        byte[] expected = ("HTTP/1.1 200 OK" +
                "\nContent-Length: 14" +
                "\nContent-Type: text/plain" +
                "\r\n\r\n").getBytes();
        String request = "/file1";
        byte[] actual = DisembodiedSousChef.craftResponseHead(directory, request, body);
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craftResponseHeadReturnsA404ResponseHeadWhenRequestNotFound() {
        byte[] body = {};
        byte[] expected = ("HTTP/1.1 404 Not Found\n" +
                "Content-Length: 0\n" +
                "Content-Type: text/plain" +
                "\r\n\r\n").getBytes();
        String request = "/not-found";
        byte[] actual = DisembodiedSousChef.craftResponseHead(directory, request, body);
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craftResponseHeadReturnsA200ResponseHeadForTea() {
        byte[] body = "I'm a teapot.".getBytes();
        byte[] expected = ("HTTP/1.1 200 OK" +
                "\nContent-Length: 13" +
                "\nContent-Type: text/plain" +
                "\r\n\r\n").getBytes();
        String request = "/tea";
        byte[] actual = DisembodiedSousChef.craftResponseHead(directory, request, body);
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craftResponseHeadReturnsA418ResponseHeadForCoffee() {
        byte[] body = "I'm a teapot.".getBytes();
        byte[] expected = ("HTTP/1.1 418 I'm a teapot." +
                "\nContent-Length: 13" +
                "\nContent-Type: text/plain" +
                "\r\n\r\n").getBytes();
        String request = "/coffee";
        byte[] actual = DisembodiedSousChef.craftResponseHead(directory, request, body);
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craft206ResponseReturnsA206ResponseHead() {
        String order = "/file1";
        long start = 2;
        long end = 13;
        byte[] body = "le1 content".getBytes();
        byte[] expected = ("HTTP/1.1 206 Partial Content" +
                "\nContent-Range: bytes 2-13/14" +
                "\nContent-Length: 11" +
                "\r\n\r\n").getBytes();
        byte[] actual = DisembodiedSousChef.craft206Response(directory, order, body, start, end);
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craft400ResponseReturnsA400Response() {
        byte[] expected = ("HTTP/1.1 400 Bad Request" +
                "\r\n\r\n").getBytes();
        byte[] actual = DisembodiedSousChef.craft400Response();
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craft401ResponseReturnsA401Response() {
        byte[] expected = ("HTTP/1.1 401 Unauthorized" +
                "\nWWW-Authenticate: Basic" +
                "\r\n\r\n").getBytes();
        byte[] actual = DisembodiedSousChef.craft401Response();
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craft416ResponseReturnsA416Response() {
        byte[] expected = ("HTTP/1.1 416 Range Not Satisfiable" +
                "\nContent-Range: */14" +
                "\r\n\r\n").getBytes();
        String url = "/file1";
        byte[] actual = DisembodiedSousChef.craft416Response(directory, url);
        assertEquals(new String(expected), new String(actual));
    }
}