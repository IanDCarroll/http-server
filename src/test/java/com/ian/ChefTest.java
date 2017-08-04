package com.ian;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ChefTest {

    @Test
    public void testChefMakes200WhenGivenRoot() {
        assertEquals("HTTP/1.1 200 OK", Chef.plate("/"));
    }

    @Test
    public void testChefMakes404WhenGivenAnythingOtherThanRoot() {
        assertEquals("HTTP/1.1 404 Not Found", Chef.plate("/A-day-not-night-to-see-till-I-see-thee"));
    }

    @Test
    public void searchMenuReturns404ResponseIfItCantFindTheRequest() {
        assertEquals("HTTP/1.1 404 Not Found", Chef.searchMenu("/iced-hot-chocolate"));
    }

    @Test
    public void searchMenuReturnsFileContents() {
        String expected = "HTTP/1.1 200 OK\r\n\r\nfile1 contents";
        assertEquals(expected, Chef.searchMenu("/file1"));
    }
}