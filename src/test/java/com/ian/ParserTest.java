package com.ian;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ParserTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void parserReturnsFormattedResponseFromChef() {
        byte[] expected = (
                "HTTP/1.1 200 OK" +
                "\r\n\r\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<a href=\"/file1\">file1</a>\n" +
                "<a href=\"/file2\">file2</a>\n" +
                "<a href=\"/form\">form</a>\n" +
                "<a href=\"/image.gif\">image.gif</a>\n" +
                "<a href=\"/image.jpeg\">image.jpeg</a>\n" +
                "<a href=\"/image.png\">image.png</a>\n" +
                "<a href=\"/partial_content.txt\">partial_content.txt</a>\n" +
                "<a href=\"/patch-content.txt\">patch-content.txt</a>\n" +
                "<a href=\"/text-file.txt\">text-file.txt</a>" +
                "\n</body>" +
                "\n</html>").getBytes();
        String request = "GET / HTTP/1.1";
        byte[] actual = Parser.parse(request, directory);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void parserIgnoresAdditionalContentPastTheRequestLine() {
        byte[] expected = (
                "HTTP/1.1 200 OK" +
                "\r\n\r\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<a href=\"/file1\">file1</a>\n" +
                "<a href=\"/file2\">file2</a>\n" +
                "<a href=\"/form\">form</a>\n" +
                "<a href=\"/image.gif\">image.gif</a>\n" +
                "<a href=\"/image.jpeg\">image.jpeg</a>\n" +
                "<a href=\"/image.png\">image.png</a>\n" +
                "<a href=\"/partial_content.txt\">partial_content.txt</a>\n" +
                "<a href=\"/patch-content.txt\">patch-content.txt</a>\n" +
                "<a href=\"/text-file.txt\">text-file.txt</a>" +
                "\n</body>" +
                "\n</html>").getBytes();
        String request = "GET / HTTP/1.1" +
                         "\nHost: [rsid].112.2o7.net" +
                         "\nX-Forwarded-For: 192.168.10.1";
        byte[] actual = Parser.parse(request, directory);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void parserParsesTheRootRequestedURI() {
        String expected = "/";
        String request = "GET / HTTP/1.1";
        Parser.parse(request, directory);
        assertEquals(expected, Parser.requestedURI);
    }

    @Test
    public void parserParsesTheSpecificRequestedURI() {
        String expected = "/shoes-and-ships-and-ceiling-wax";
        String request = "GET /shoes-and-ships-and-ceiling-wax HTTP/1.1";
        Parser.parse(request, directory);
        assertEquals(expected, Parser.requestedURI);
    }

    @Test
    public void parserHandlesNullRequests() {
        byte[] expected = null;
        byte[] actual = Parser.parse(null, directory);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void parseHeadSetsTheRequestStartLineValues() {
        String request = "PUT /basho HTTP/1.1" +
                "\ncontent-type: text/plain\r\n\r\n" +
                "ancient pond\n" +
                "a frog jumps in\r\n\r\n" +
                "sound of water";
        Parser.parseHead(request);
        assertEquals("PUT", Parser.requestMethod);
        assertEquals("/basho", Parser.requestedURI);
        assertEquals("HTTP/1.1", Parser.httpVersion);
    }

    @Test
    public void setRequestBodySavesTheBodyOfTheRequestForReference() {
        String body = "ancient pond\n" +
                      "a frog jumps in\r\n\r\n" +
                      "sound of water";
        String request = "PUT /basho HTTP/1.1\n" +
                "content-type: text/plain\r\n\r\n" + body;
        Parser.setRequestBody(request);
        String actual = Parser.requestBody;
        assertEquals(body, actual);

    }

    @Test
    public void applyAppropriateMethodDirectsRequestsByTheirRequestMethod() {
        Parser.directory = directory;
        Parser.requestMethod = "GET";
        Parser.requestedURI = "/the-lost-city-of-atlantis";
        Parser.httpVersion = "HTTP/1.1";
        byte[] expected = "HTTP/1.1 404 Not Found\r\n\r\n".getBytes();
        byte[] actual = Parser.applyAppropriateMethod();
        assertArrayEquals(expected, actual);
        Parser.resetParser();
    }

    @Test
    public void headReturnsOnlyTheStartLineOfGETRequest() {
        Parser.directory = directory;
        Parser.requestedURI = "/";
        byte[] expected = "HTTP/1.1 200 OK".getBytes();
        byte[] actual = Parser.head();
        assertArrayEquals(expected, actual);
        Parser.resetParser();
    }

    @Test
    public void getReturnsTheResultsOfGETRequest() {
        Parser.directory = directory;
        Parser.requestedURI = "/the-lost-city-of-atlantis";
        byte[] expected = "HTTP/1.1 404 Not Found\r\n\r\n".getBytes();
        byte[] actual = Parser.get();
        assertArrayEquals(expected, actual);
        Parser.resetParser();
    }

    @Test
    public void putCreatesAFileWithItsBodyContentsWhenNoFileExists() {
        Parser.directory = directory;
        Parser.requestedURI = "/the-lost-city-of-atlantis";
        byte[] expected = "HTTP/1.1 404 Not Found\r\n\r\n".getBytes();
        byte[] actual = Parser.get();
        assertArrayEquals(expected, actual);
        Parser.resetParser();
    }

    @Test
    public void resetParserResetsAllPublicStaticVariables() {
        String expected = "";
        String oldCrustyRequest = "GET / HTTP/1.1";
        Parser.parse(oldCrustyRequest, directory);
        Parser.resetParser();
        assertEquals(expected, Parser.directory);
        assertEquals(expected, Parser.requestMethod);
        assertEquals(expected, Parser.requestedURI);
        assertEquals(expected, Parser.httpVersion);
    }
}