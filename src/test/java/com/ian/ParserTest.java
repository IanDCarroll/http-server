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
}