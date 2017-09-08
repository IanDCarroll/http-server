package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class SuccessCookTest {
    @Test
    public void craft4XXResponseReturns400response() {
        byte[] expected = "HTTP/1.1 200 OK".getBytes();
        byte[] actual = SuccessCook.craft2XXResponse();
        assertEquals(new String(expected), new String(actual));
    }
}