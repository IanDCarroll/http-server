package com.ian;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ChefTest {
    public static final String directory = "/Users/ian/cob_spec/public";

    @Test
    public void testChefMakes200WhenGivenRoot() {
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", Chef.plate(directory,"/"));
    }

    @Test
    public void testChefMakes404WhenGivenAnythingOtherThanRoot() {
        assertEquals("HTTP/1.1 404 Not Found", Chef.plate(directory,"/A-day-not-night-to-see-till-I-see-thee"));
    }

    @Test
    public void searchMenuReturns404ResponseIfItCantFindTheRequest() {
        assertEquals("HTTP/1.1 404 Not Found", Chef.searchMenu(directory,"/iced-hot-chocolate"));
    }

    @Test
    public void searchMenuReturnsFileContents() {
        String expected = "HTTP/1.1 200 OK\r\n\r\nfile1 contents";
        assertEquals(expected, Chef.searchMenu(directory,"/file1"));
    }
}