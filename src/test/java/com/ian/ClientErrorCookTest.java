package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientErrorCookTest {
    @Test
    public void craft404ResponseReturns400response() {
        byte[] expected = "HTTP/1.1 404 Not Found".getBytes();
        byte[] actual = ClientErrorCook.craft404Response();
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craft418ResponseReturns418response() {
        byte[] expected = "HTTP/1.1 418 I'm a teapot.".getBytes();
        byte[] actual = ClientErrorCook.craft418Response();
        assertEquals(new String(expected), new String(actual));
    }
}