package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class DisembodiedSousChefTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void craftResponseHeadReturnsA200ResponseHeadWhenRequestFound() {
        //GIVEN
        byte[] expected = ("HTTP/1.1 200 OK" +
                "\nContent-Length: 14" +
                "\nContent-Type: text/plain" +
                "\r\n\r\n").getBytes();
        String request = "/file1";
        byte[] body = "file1 contents".getBytes();
        //WHEN
        byte[] actual = DisembodiedSousChef.craftResponseHead(directory, request, body);
        //THEN
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craftResponseHeadReturnsA404ResponseHeadWhenRequestNotFound() {
        //GIVEN
        byte[] expected = ("HTTP/1.1 404 Not Found\n" +
                "Content-Length: 0\n" +
                "Content-Type: text/plain" +
                "\r\n\r\n").getBytes();
        String request = "/not-found";
        //WHEN
        byte[] actual = DisembodiedSousChef.craftResponseHead(directory, request, new byte[0]);
        //THEN
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craftResponseHeadReturnsA200ResponseHeadForTea() {
        //GIVEN
        byte[] expected = ("HTTP/1.1 200 OK" +
                "\nContent-Length: 13" +
                "\nContent-Type: text/plain" +
                "\r\n\r\n").getBytes();
        String request = "/tea";
        byte[] body = "I'm a teapot.".getBytes();
        //WHEN
        byte[] actual = DisembodiedSousChef.craftResponseHead(directory, request, body);
        //THEN
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craftResponseHeadReturnsA418ResponseHeadForCoffee() {
        //GIVEN
        byte[] expected = ("HTTP/1.1 418 I'm a teapot." +
                "\nContent-Length: 13" +
                "\nContent-Type: text/plain" +
                "\r\n\r\n").getBytes();
        String request = "/coffee";
        byte[] body = "I'm a teapot.".getBytes();
        //WHEN
        byte[] actual = DisembodiedSousChef.craftResponseHead(directory, request, body);
        //THEN
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craft206ResponseReturnsA206ResponseHead() {
        //GIVEN
        byte[] expected = ("HTTP/1.1 206 Partial Content" +
                "\nContent-Range: bytes 2-13/14" +
                "\nContent-Length: 11" +
                "\r\n\r\n").getBytes();
        String order = "/file1";
        byte[] body = "le1 content".getBytes();
        long start = 2;
        long end = 13;
        //WHEN
        byte[] actual = DisembodiedSousChef.craft206Response(directory, order, body, start, end);
        //THEN
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
        //GIVEN
        byte[] expected = ("HTTP/1.1 416 Range Not Satisfiable" +
                "\nContent-Range: */14" +
                "\r\n\r\n").getBytes();
        String url = "/file1";
        //WHEN
        byte[] actual = DisembodiedSousChef.craft416Response(directory, url);
        //THEN
        assertEquals(new String(expected), new String(actual));
    }
}