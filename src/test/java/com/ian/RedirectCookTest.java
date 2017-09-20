package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class RedirectCookTest {
    @Test
    public void craft302ResponseCrafts302StartLineAndLocationHeader() {
        //GIVEN
        byte[] expected = ("HTTP/1.1 302 Found" +
                           "\nLocation: /").getBytes();
        String request = "/redirect";
        //WHEN
        byte[] actual = RedirectCook.craft302Response(request);
        //THEN
        assertEquals(new String(expected), new String(actual));
    }
}