package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientErrorCookTest {
    @Test
    public void craft4XXResponseReturns400response() {
        byte[] expected = "HTTP/1.1 404 Not Found".getBytes();
        byte[] actual = ClientErrorCook.craft4XXResponse();
        assertEquals(new String(expected), new String(actual));
    }
}