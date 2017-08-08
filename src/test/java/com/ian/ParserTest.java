package com.ian;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ParserTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void testParserReturnsFormattedResponseFromChef() {
        String expected = "HTTP/1.1 200 OK\r\n\r\n" +
                "file1 file2 image.gif image.jpeg image.png partial_content.txt patch-content.txt text-file.txt";
        String request = "GET / HTTP/1.1";
        assertEquals(expected, Parser.parse(request, directory));
    }

    @Test
    public void testParserIgnoresAdditionalContentPastTheRequestLine() {
        String expected = "HTTP/1.1 200 OK\r\n\r\n" +
                "file1 file2 image.gif image.jpeg image.png partial_content.txt patch-content.txt text-file.txt";
        String request = "GET / HTTP/1.1" +
                         "\nHost: [rsid].112.2o7.net" +
                         "\nX-Forwarded-For: 192.168.10.1";
        assertEquals(expected, Parser.parse(request, directory));
    }

    @Test
    public void testParserParsesTheRootRequestedURI() {
        String request = "GET / HTTP/1.1";
        Parser.parse(request, directory);
        assertEquals("/", Parser.requestedURI);
    }

    @Test
    public void testParserParsesTheSpecificRequestedURI() {
        String request = "GET /shoes-and-ships-and-ceiling-wax HTTP/1.1";
        Parser.parse(request, directory);
        assertEquals("/shoes-and-ships-and-ceiling-wax", Parser.requestedURI);
    }
}