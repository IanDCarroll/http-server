package com.ian;

import org.junit.Test;
import java.util.HashMap;
import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void parserReturnsAHashOfRawButSeparatedRequestElements() {
        //GIVEN
        HashMap<String, String> expected = new HashMap<>();
        expected.put("method", "GET");
        expected.put("url", "/");
        expected.put("urlParams", "");
        expected.put("httpVersion", "HTTP/1.1");
        expected.put("headers", "");
        expected.put("body", "");
        String request = "GET / HTTP/1.1";
        //WHEN
        HashMap actual = Parser.parse(request);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void parserReturnsAHashFromRequestPlusCRLF() {
        //GIVEN
        HashMap<String, String> expected = new HashMap<>();
        expected.put("method", "GET");
        expected.put("url", "/");
        expected.put("urlParams", "");
        expected.put("httpVersion", "HTTP/1.1");
        expected.put("headers", "");
        expected.put("body", "");
        String request = "GET / HTTP/1.1\r\n\r\n";
        //WHEN
        HashMap actual = Parser.parse(request);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void parserReturnsAHashFromRequestPlusHeaders() {
        //GIVEN
        HashMap<String, String> expected = new HashMap<>();
        expected.put("method", "GET");
        expected.put("url", "/");
        expected.put("urlParams", "");
        expected.put("httpVersion", "HTTP/1.1");
        expected.put("headers", "random: headers\nother: headers");
        expected.put("body", "");
        String request = "GET / HTTP/1.1" +
                "\nrandom: headers" +
                "\nother: headers" +
                "\r\n\r\n";
        //WHEN
        HashMap actual = Parser.parse(request);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void parserHandlesNullRequests() {
        //GIVEN
        HashMap<String, String> expected = new HashMap<>();
        expected.put("method", "");
        expected.put("url", "");
        expected.put("urlParams", "");
        expected.put("httpVersion", "");
        expected.put("headers", "");
        expected.put("body", "");
        //WHEN
        HashMap actual = Parser.parse(null);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void parserParsesParametersRaw() {
        //GIVEN
        String expected = "name=Sir%20Lancelot&quest=To%20Seek%20the%20Holy%20Grail";
        String request = "GET /?" + expected + " HTTP/1.1";
        //WHEN
        String actual = Parser.parse(request).get("urlParams");
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void parserSavesTheBodyOfTheRequest() {
        //GIVEN
        String body = "ancient pond\n" +
                "a frog jumps in\r\n\r\n" +
                "sound of water";
        String request = "PUT /basho HTTP/1.1\n" +
                "content-type: text/plain\r\n\r\n" + body;
        //WHEN
        Parser.parse(request);
        //THEN
        String actual = Parser.parsedRequest.get("body");
        assertEquals(body, actual);

    }
}