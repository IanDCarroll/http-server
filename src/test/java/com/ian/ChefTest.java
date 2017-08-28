package com.ian;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import java.io.File;

public class ChefTest {
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
                "<a href=\"/image.gif\">image.gif</a>\n" +
                "<a href=\"/image.jpeg\">image.jpeg</a>\n" +
                "<a href=\"/image.png\">image.png</a>\n" +
                "<a href=\"/partial_content.txt\">partial_content.txt</a>\n" +
                "<a href=\"/patch-content.txt\">patch-content.txt</a>\n" +
                "<a href=\"/text-file.txt\">text-file.txt</a>" +
                "\n</body>" +
                "\n</html>";
        String[] order = {"/"};
        String actual = new String(Chef.plate(directory, order));
        assertEquals(expected, actual);
    }

    @Test
    public void plateCallsCookOrderIfOrderIsAFile() {
        String expected = "HTTP/1.1 200 OK" +
                "\nContent-Length: 14" +
                "\nContent-Type: text/plain" +
                "\r\n\r\n" +
                "file1 contents";
        String[] order = {"/file1"};
        String actual = new String(Chef.plate(directory, order));
        assertEquals(expected, actual);
    }

    @Test
    public void plateReturns404IfOrderDoesNotExist() {
        String expected = "HTTP/1.1 404 Not Found" +
                "\r\n\r\n";
        String[] order = {"/the_holy_grail"};
        String actual = new String(Chef.plate(directory, order));
        assertEquals( expected, actual);
    }

    @Test
    public void plateReturns404WithParamsIfOrderDoesNotExist() {
        String expected = "HTTP/1.1 404 Not Found" +
                "\r\n\r\n" +
                "looks = nice\n" +
                "cost = not too expensive";
        String[] order = {"/shrubbery",
                "looks = nice",
                "cost = not too expensive"};
        String actual = new String(Chef.plate(directory, order));
        assertEquals( expected, actual);
    }

    @Test
    public void plateReturnsFileContents() {
        String expected = "HTTP/1.1 200 OK" +
                          "\nContent-Length: 14" +
                          "\nContent-Type: text/plain" +
                          "\r\n\r\n" +
                          "file1 contents" +
                          "param1 = other contents";
        String[] order = {"/file1",
                          "param1 = other contents"};
        String actual = new String(Chef.plate(directory, order));
        assertEquals(expected, actual);
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
                "<a href=\"/file1\">file1</a>\n" +
                "<a href=\"/file2\">file2</a>\n" +
                "<a href=\"/image.gif\">image.gif</a>\n" +
                "<a href=\"/image.jpeg\">image.jpeg</a>\n" +
                "<a href=\"/image.png\">image.png</a>\n" +
                "<a href=\"/partial_content.txt\">partial_content.txt</a>\n" +
                "<a href=\"/patch-content.txt\">patch-content.txt</a>\n" +
                "<a href=\"/text-file.txt\">text-file.txt</a>" +
                "\n</body>" +
                "\n</html>" +
                "looks = nice\n" +
                "cost = not too expensive";
        String[] order = {"/",
                "looks = nice",
                "cost = not too expensive"};
        String actual = new String(Chef.plate(directory, order));
        assertEquals( expected, actual);
    }

    @Test
    public void menuGivesAListingOfDirectoryContents() {
        File file = new File(directory, "/");
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
        String actual = new String(Chef.menuDuJour(file));
        assertEquals(expected, actual);
    }

    @Test
    public void cookOrderReturnsFileContents() {
        File file = new File(directory, "/file1");
        String expected = "HTTP/1.1 200 OK" +
                "\nContent-Length: 14" +
                "\nContent-Type: text/plain" +
                "\r\n\r\n" +
                "file1 contents";
        String actual = new String(Chef.cookOrder(file));
        assertEquals(expected, actual);
    }

    @Test
    public void resetParamsResetsTheParamsToZeroBytes() {
        byte[] expected = new byte[0];
        Chef.params = "param = old crusty data".getBytes();
        Chef.resetParams();
        assertArrayEquals(expected, Chef.params);
    }
}