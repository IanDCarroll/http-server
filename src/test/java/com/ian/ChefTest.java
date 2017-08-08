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
        String expected = "HTTP/1.1 200 OK\r\n\r\nfile1 file2 image.gif image.jpeg image.png partial_content.txt patch-content.txt text-file.txt";
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
        String expected = "HTTP/1.1 200 OK\r\n\r\nfile1 file2 image.gif image.jpeg image.png partial_content.txt patch-content.txt text-file.txt";
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