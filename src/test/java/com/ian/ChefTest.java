package com.ian;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.io.File;

public class ChefTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void menuGivesAListingOfDirectoryContents() {
        File file = new File(directory, "/");
        String expected = "HTTP/1.1 200 OK\r\n\r\n" +
                "<a href=\"file1\">file1</a>\n" +
                "<a href=\"file2\">file2</a>\n" +
                "<a href=\"image.gif\">image.gif</a>\n" +
                "<a href=\"image.jpeg\">image.jpeg</a>\n" +
                "<a href=\"image.png\">image.png</a>\n" +
                "<a href=\"partial_content.txt\">partial_content.txt</a>\n" +
                "<a href=\"patch-content.txt\">patch-content.txt</a>\n" +
                "<a href=\"text-file.txt\">text-file.txt</a>";
        assertEquals(expected, Chef.menuDuJour(file));
    }

    @Test
    public void cookOrderReturnsFileContents() {
        File file = new File(directory, "/file1");
        String expected = "HTTP/1.1 200 OK\r\n\r\nfile1 contents";
        assertEquals(expected, Chef.cookOrder(file));
    }

    @Test
    public void searchMenuCallsMenuDuJourIfOrderIsADirectory() {
        String expected = "HTTP/1.1 200 OK\r\n\r\n" +
                "<a href=\"file1\">file1</a>\n" +
                "<a href=\"file2\">file2</a>\n" +
                "<a href=\"image.gif\">image.gif</a>\n" +
                "<a href=\"image.jpeg\">image.jpeg</a>\n" +
                "<a href=\"image.png\">image.png</a>\n" +
                "<a href=\"partial_content.txt\">partial_content.txt</a>\n" +
                "<a href=\"patch-content.txt\">patch-content.txt</a>\n" +
                "<a href=\"text-file.txt\">text-file.txt</a>";
        assertEquals(expected, Chef.plate(directory, "/"));
    }

    @Test
    public void searchMenuCallsCookOrderIfOrderIsAFile() {
        String expected = "HTTP/1.1 200 OK\r\n\r\nfile1 contents";
        assertEquals(expected, Chef.plate(directory, "/file1"));
    }

    @Test
    public void searchMenuReturns404IfOrderDoesNotExist() {
        assertEquals("HTTP/1.1 404 Not Found", Chef.plate(directory,"/The_holy_grail"));
    }
}