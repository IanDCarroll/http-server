package com.ian;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ParserTest {

    @Test
    public void testParserReturnsFormattedResponseWhenRootIsRequested() {
        String request = "GET / HTTP/1.1";
        assertEquals("HTTP/1.1 200 OK", Parser.parse(request));
    }

    @Test
    public void testParserReturns404ResponseWhenAnythingOtherThanRootIsRequested() {
        String request = "GET /A-day-not-night-to-see-till-I-see-thee HTTP/1.1";
        assertEquals("HTTP/1.1 404 Not Found", Parser.parse(request));
    }

    @Test
    public void testParserIgnoresAdditionalContentPastTheRequestLine() {
        String request = "GET / HTTP/1.1" +
                         "\nHost: [rsid].112.2o7.net" +
                         "\nX-Forwarded-For: 192.168.10.1";
        assertEquals("HTTP/1.1 200 OK", Parser.parse(request));
    }
}