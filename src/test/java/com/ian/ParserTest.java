package com.ian;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ParserTest {

    @Test
    public void testParserReturnsFormattedResponseFromChef() {
        String request = "GET / HTTP/1.1";
        assertEquals("HTTP/1.1 200 OK", Parser.parse(request));
    }

    @Test
    public void testParserIgnoresAdditionalContentPastTheRequestLine() {
        String request = "GET / HTTP/1.1" +
                         "\nHost: [rsid].112.2o7.net" +
                         "\nX-Forwarded-For: 192.168.10.1";
        assertEquals("HTTP/1.1 200 OK", Parser.parse(request));
    }

    @Test
    public void testParserParsesTheRootRequestedURI() {
        String request = "GET / HTTP/1.1";
        Parser.parse(request);
        assertEquals("/", Parser.requestedURI);
    }

    @Test
    public void testParserParsesTheSpecificRequestedURI() {
        String request = "GET /shoes-and-ships-and-ceiling-wax HTTP/1.1";
        Parser.parse(request);
        assertEquals("/shoes-and-ships-and-ceiling-wax", Parser.requestedURI);
    }
}