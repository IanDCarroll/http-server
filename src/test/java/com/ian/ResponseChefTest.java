package com.ian;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class ResponseChefTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void craftResponseCallsMenuDuJourIfOrderIsADirectory() {
        //GIVEN
        String expected = "HTTP/1.1 200 OK" +
                "\nContent-Length: 436" +
                "\nContent-Type: text/html" +
                "\r\n\r\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<a href=\"/form\">form</a>\n" +
                "<a href=\"/text-file.txt\">text-file.txt</a>\n" +
                "<a href=\"/file2\">file2</a>\n" +
                "<a href=\"/patch-content.txt\">patch-content.txt</a>\n" +
                "<a href=\"/image.gif\">image.gif</a>\n" +
                "<a href=\"/logs\">logs</a>\n" +
                "<a href=\"/image.jpeg\">image.jpeg</a>\n" +
                "<a href=\"/file1\">file1</a>\n" +
                "<a href=\"/partial_content.txt\">partial_content.txt</a>\n" +
                "<a href=\"/image.png\">image.png</a>" +
                "\n</body>" +
                "\n</html>";
        String order = "/";
        String[] params = {};
        //WHEN
        String actual = new String(ResponseChef.craftResponse(directory, order, params));
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void craftResponseCallsCookOrderIfOrderIsAFile() {
        //GIVEN
        String expected = "HTTP/1.1 200 OK" +
                "\nContent-Length: 14" +
                "\nContent-Type: text/plain" +
                "\r\n\r\n" +
                "file1 contents";
        String order = "/file1";
        String[] params = {};
        //WHEN
        String actual = new String(ResponseChef.craftResponse(directory, order, params));
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void craftResponseReturns404IfOrderDoesNotExist() {
        //GIVEN
        String expected = "HTTP/1.1 404 Not Found" +
                "\nContent-Length: 184" +
                "\nContent-Type: text/plain" +
                "\r\n\r\n"+
                "This is the Corpulent Sous Chef.\n" +
                "on behalf of our Cafe, I must personally apologize for not having your request on the menu.\n" +
                "It is our deepest regret we could not serve you this thing.";
        String order = "/the_holy_grail";
        String[] params = {};
        //WHEN
        String actual = new String(ResponseChef.craftResponse(directory, order, params));
        //THEN
        assertEquals( expected, actual);
    }

    @Test
    public void craftResponseReturns418IfHTCPCPRequestsCoffee() {
        //GIVEN
        String expected = "HTTP/1.1 418 I'm a teapot." +
                "\nContent-Length: 13" +
                "\nContent-Type: text/plain" +
                "\r\n\r\n"+
                "I'm a teapot.";
        String order = "/coffee";
        String[] params = {};
        //WHEN
        String actual = new String(ResponseChef.craftResponse(directory, order, params));
        //THEN
        assertEquals( expected, actual);
    }

    @Test
    public void craftResponseReturns200IfHTCPCPRequestsTea() {
        //GIVEN
        String expected = "HTTP/1.1 200 OK\n" +
                "Content-Length: 13\n" +
                "Content-Type: text/plain" +
                "\r\n\r\n" +
                "I'm a teapot.";
        String order = "/tea";
        String[] params = {};
        //WHEN
        String actual = new String(ResponseChef.craftResponse(directory, order, params));
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void craftResponseReturns200WithParamsIfOrderIsDirectory() {
        //GIVEN
        String expected = "<p>looks = nice</p>\n" +
                "<p>cost = not too expensive</p>";
        String order = "/";
        String[] params = {"looks=nice",
                           "cost=not too expensive"};
        //WHEN
        String actual = new String(ResponseChef.craftResponse(directory, order, params));
        //THEN
        assertEquals(true, actual.contains(expected));
    }

    @Test
    public void craftPartialResponseReturnsA206Response() {
        //GIVEN
        String expected = "HTTP/1.1 206 Partial Content" +
                "\nContent-Range: bytes 2-12/14" +
                "\nContent-Length: 11" +
                "\r\n\r\n" +
                "le1 content";
        String order = "/file1";
        long start = 2;
        long end = 12;
        //WHEN
        String actual = new String(ResponseChef.craftPartialResponse(directory, order, start, end));
        //THEN
        assertEquals(expected, actual);
    }
}