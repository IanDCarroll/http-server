package com.ian;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import java.io.IOException;

public class ServSocketTest {

    // Integration tests that require manual work:
    // Run the following command in the terminal, then un-ignore tests here.
    // $ java -jar ./target/http-server-1.0-SNAPSHOT.jar
    @Test
    @Ignore
    public void testClientSocketGetsHTTP200ResponseWhenRootIsRequested() {
        String expected;
        try { expected = ClientSocketHelper.request("GET / HTTP/1.1");
        } catch ( IOException e ) { expected = e.getMessage(); }
        assertEquals("HTTP/1.1 200 OK", expected);
    }

    @Test
    @Ignore
    public void testClientSocketGetsHTTP200ResponseWhenAnythingOtherThanRootIsRequested() {
        String expected;
        try { expected = ClientSocketHelper.request("GET /a-day-not-night-to-see-till-i-see-thee HTTP/1.1");
        } catch ( IOException e ) { expected = e.getMessage(); }
        assertEquals("HTTP/1.1 404 Not Found", expected);
    }
}