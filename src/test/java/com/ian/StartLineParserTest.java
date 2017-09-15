package com.ian;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class StartLineParserTest {
    @Test
    public void startLineParserReturnsAHashOfRawButSeparatedElements() {
        //GIVEN
        HashMap<String, String> expected = new HashMap<>();
        expected.put("method", "GET");
        expected.put("url", "/");
        expected.put("urlParams", "");
        expected.put("httpVersion", "HTTP/1.1");
        String request = "GET / HTTP/1.1";
        //WHEN
        HashMap actual = StartLineParser.parseStartLine(request);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void startLineParserParsesParametersRaw() {
        //GIVEN
        String expected = "name=Sir%20Lancelot&quest=To%20Seek%20the%20Holy%20Grail";
        String request = "GET /?" + expected + " HTTP/1.1";
        //WHEN
        String actual = StartLineParser.parseStartLine(request).get("urlParams");
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void parseStartLineSetsTheRequestStartLineValues() {
        //GIVEN
        String request = "PUT /basho HTTP/1.1" +
                "\ncontent-type: text/plain\r\n\r\n" +
                "ancient pond\n" +
                "a frog jumps in\r\n\r\n" +
                "sound of water";
        //WHEN
        StartLineParser.parseStartLine(request);
        //THEN
        assertEquals("PUT", StartLineParser.parsedStartLine.get("method"));
        assertEquals("/basho", StartLineParser.parsedStartLine.get("url"));
        assertEquals("HTTP/1.1", StartLineParser.parsedStartLine.get("httpVersion"));
    }
}