package com.ian;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ParserTest {

    @Test
    public void testParserReturnsFormattedResponseWhenRootIsRequested()
    { assertEquals("HTTP/1.1 200 OK", Parser.parse("GET / HTTP/1.1")); }

    @Test
    public void testParserReturns404ResponseWhenAnythingOtherThanRootIsRequested()
    { assertEquals("HTTP/1.1 404 Not Found", Parser.parse("GET /A-day-not-night-to-see-till-I-see-thee HTTP/1.1")); }

    @Test
    public void testParserIgnoresAdditionalContentPastTheRequestLine()
    { assertEquals("HTTP/1.1 200 OK", Parser.parse("GET / HTTP/1.1\n" +
                                                                    "Host: [rsid].112.2o7.net\n" +
                                                                    "X-Forwarded-For: 192.168.10.1")); }
}