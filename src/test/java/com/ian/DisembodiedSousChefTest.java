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
    public void craft400ResponseHeadReturnsA400Response() {
        byte[] expected = ("HTTP/1.1 400 Bad Request" +
                "\r\n\r\n").getBytes();
        byte[] actual = DisembodiedSousChef.craft400Response();
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craft401ResponseHeadReturnsA401Response() {
        byte[] expected = ("HTTP/1.1 401 Unauthorized" +
                "\nWWW-Authenticate: Basic" +
                "\r\n\r\n").getBytes();
        byte[] actual = DisembodiedSousChef.craft401Response();
        assertEquals(new String(expected), new String(actual));
    }
}