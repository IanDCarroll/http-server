package com.ian;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ResponseChefTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void plateCallsMenuDuJourIfOrderIsADirectory() {
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
                "<a href=\"/form\">form</a>\n" +
                "<a href=\"/image.gif\">image.gif</a>\n" +
                "<a href=\"/image.jpeg\">image.jpeg</a>\n" +
                "<a href=\"/image.png\">image.png</a>\n" +
                "<a href=\"/partial_content.txt\">partial_content.txt</a>\n" +
                "<a href=\"/patch-content.txt\">patch-content.txt</a>\n" +
                "<a href=\"/text-file.txt\">text-file.txt</a>" +
                "\n</body>" +
                "\n</html>";
        String order = "/";
        String[] params = {};
        String actual = new String(ResponseChef.craftResponse(directory, order, params));
        assertEquals(expected, actual);
    }

    @Test
    public void plateCallsCookOrderIfOrderIsAFile() {
        String expected = "HTTP/1.1 200 OK" +
                "\nContent-Length: 14" +
                "\nContent-Type: text/plain" +
                "\r\n\r\n" +
                "file1 contents";
        String order = "/file1";
        String[] params = {};
        String actual = new String(ResponseChef.craftResponse(directory, order, params));
        assertEquals(expected, actual);
    }

    @Test
    public void plateReturns404IfOrderDoesNotExist() {
        String expected = "HTTP/1.1 404 Not Found" +
                "\r\n\r\n";
        String order = "/the_holy_grail";
        String[] params = {};
        String actual = new String(ResponseChef.craftResponse(directory, order, params));
        assertEquals( expected, actual);
    }

    @Test
    public void plateReturns404WithParamsIfOrderDoesNotExist() {
        String expected = "HTTP/1.1 404 Not Found" +
                "\r\n\r\n" +
                "looks = nicecost = not too expensive";
        String order = "/shrubbery";
        String[] params = {"looks = nice",
                           "cost = not too expensive"};
        String actual = new String(ResponseChef.craftResponse(directory, order, params));
        assertEquals( expected, actual);
    }

    @Test
    public void searchMenuReturns200WithParamsIfOrderIsDirectory() {
        String expected = "HTTP/1.1 200 OK" +
                "\r\n\r\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>looks = nicecost = not too expensive</p>" +
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
                "\n</html>" +
                "looks = nicecost = not too expensive";
        String order = "/";
        String[] params = {"looks = nice",
                           "cost = not too expensive"};
        String actual = new String(ResponseChef.craftResponse(directory, order, params));
        assertEquals( expected, actual);
    }

    @Test
    public void menuGivesAListingOfDirectoryContents() {
        String fileName = "/";
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
                "<a href=\"/form\">form</a>\n" +
                "<a href=\"/image.gif\">image.gif</a>\n" +
                "<a href=\"/image.jpeg\">image.jpeg</a>\n" +
                "<a href=\"/image.png\">image.png</a>\n" +
                "<a href=\"/partial_content.txt\">partial_content.txt</a>\n" +
                "<a href=\"/patch-content.txt\">patch-content.txt</a>\n" +
                "<a href=\"/text-file.txt\">text-file.txt</a>" +
                "\n</body>" +
                "\n</html>";
        String actual = new String(ResponseChef.menuDuJour(directory, fileName));
        assertEquals(expected, actual);
    }

    @Test
    public void cookOrderReturnsFileContents() {
        String expected = "HTTP/1.1 200 OK" +
                "\nContent-Length: 14" +
                "\nContent-Type: text/plain" +
                "\r\n\r\n" +
                "file1 contents";
        String actual = new String(ResponseChef.cookOrder(directory, "/file1"));
        assertEquals(expected, actual);
    }

    @Test
    public void resetParamBytesResetsTheParamsToZeroBytes() {
        byte[] expected = {};
        ResponseChef.paramBytes = "param = old crusty data".getBytes();
        ResponseChef.resetParamBytes();
        assertArrayEquals(expected, ResponseChef.paramBytes);
    }
}