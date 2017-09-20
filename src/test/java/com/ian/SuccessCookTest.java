package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class SuccessCookTest {
    @Test
    public void craft200ResponseReturns200response() {
        byte[] expected = "HTTP/1.1 200 OK".getBytes();
        byte[] actual = SuccessCook.craft200Response();
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craft206ResponseReturns206response() {
        byte[] expected = "HTTP/1.1 206 Partial Content".getBytes();
        byte[] actual = SuccessCook.craft206Response();
        assertEquals(new String(expected), new String(actual));
    }
}