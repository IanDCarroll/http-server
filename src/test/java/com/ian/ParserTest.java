package com.ian;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ParserTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void parserReturnsFormattedResponseFromChef() {
        String expected = "HTTP/1.1 200 OK" +
                "\r\n\r\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<a href=\"/file1\">file1</a>\n" +
                "<a href=\"/file2\">file2</a>\n" +
                "<a href=\"/image.gif\">image.gif</a>\n" +
                "<a href=\"/image.jpeg\">image.jpeg</a>\n" +
                "<a href=\"/image.png\">image.png</a>\n" +
                "<a href=\"/partial_content.txt\">partial_content.txt</a>\n" +
                "<a href=\"/patch-content.txt\">patch-content.txt</a>\n" +
                "<a href=\"/text-file.txt\">text-file.txt</a>" +
                "\n</body>" +
                "\n</html>";
        String request = "GET / HTTP/1.1";
        String actual = new String(Parser.parse(request, directory));
        assertEquals(expected, actual);
    }

    @Test
    public void parserIgnoresAdditionalContentPastTheRequestLine() {
        String expected = "HTTP/1.1 200 OK" +
                "\r\n\r\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<a href=\"/file1\">file1</a>\n" +
                "<a href=\"/file2\">file2</a>\n" +
                "<a href=\"/image.gif\">image.gif</a>\n" +
                "<a href=\"/image.jpeg\">image.jpeg</a>\n" +
                "<a href=\"/image.png\">image.png</a>\n" +
                "<a href=\"/partial_content.txt\">partial_content.txt</a>\n" +
                "<a href=\"/patch-content.txt\">patch-content.txt</a>\n" +
                "<a href=\"/text-file.txt\">text-file.txt</a>" +
                "\n</body>" +
                "\n</html>";
        String request = "GET / HTTP/1.1" +
                         "\nHost: [rsid].112.2o7.net" +
                         "\nX-Forwarded-For: 192.168.10.1";
        String actual = new String(Parser.parse(request, directory));
        assertEquals(expected, actual);
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
        String expected = null;
        Parser.parse(null, directory);
        assertEquals(expected, Parser.requestedURI);
    }
}