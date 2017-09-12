package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class RedirectCookTest {
    @Test
    public void craft302ResponseCrafts302StartLineAndLocationHeader() {
        String request = "/redirect";
        byte[] expected = ("HTTP/1.1 302 Found" +
                "\nLocation: /").getBytes();
        byte[] actual = RedirectCook.craft302Response(request);
        assertEquals(new String(expected), new String(actual));
    }
}