package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientErrorCookTest {
    @Test
    public void craft400ResponseReturns400response() {
        byte[] expected = "HTTP/1.1 400 Bad Request".getBytes();
        byte[] actual = ClientErrorCook.craft400Response();
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craft401ResponseReturns401response() {
        byte[] expected = "HTTP/1.1 401 Unauthorized".getBytes();
        byte[] actual = ClientErrorCook.craft401Response();
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craft404ResponseReturns404response() {
        byte[] expected = "HTTP/1.1 404 Not Found".getBytes();
        byte[] actual = ClientErrorCook.craft404Response();
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craft416ResponseReturns416response() {
        byte[] expected = "HTTP/1.1 416 Range Not Satisfiable".getBytes();
        byte[] actual = ClientErrorCook.craft416Response();
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craft418ResponseReturns418response() {
        byte[] expected = "HTTP/1.1 418 I'm a teapot.".getBytes();
        byte[] actual = ClientErrorCook.craft418Response();
        assertEquals(new String(expected), new String(actual));
    }
}