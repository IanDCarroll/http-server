package com.ian;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ChefTest {

    @Test
    public void testChefMakes200WhenGivenRoot() {
        assertEquals("200 OK", Chef.plate("/"));
    }

    @Test
    public void testChefMakes404WhenGivenAnythingOtherThanRoot() {
        assertEquals("404 Not Found", Chef.plate("/A-day-not-night-to-see-till-I-see-thee"));
    }
}