package com.ian;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import java.io.IOException;

public class ServSocketTest {

    @BeforeClass
    public static void startUp() {
        class SeparateThread implements Runnable {
            public void run() {
                ServSocket.serve(5000);
            }
        }
        new Thread(new SeparateThread()).start();
    }

    @Test
    public void testClientSocketGetsHTTP200ResponseWhenRootIsRequested() {
        try { Thread.sleep(1); } catch (InterruptedException e){ e.getMessage(); }
        String expected;
        try { expected = ClientSocketHelper.request("GET / HTTP/1.1");
        } catch ( IOException e ) { expected = e.getMessage(); }
        assertEquals("HTTP/1.1 200 OK", expected);
    }

    @Test
    public void testClientSocketGetsHTTP404ResponseWhenAnythingOtherThanRootIsRequested() {
        try { Thread.sleep(1); } catch (InterruptedException e){ e.getMessage(); }
        String expected;
        try { expected = ClientSocketHelper.request("GET /a-day-not-night-to-see-till-i-see-thee HTTP/1.1");
        } catch ( IOException e ) { expected = e.getMessage(); }
        assertEquals("HTTP/1.1 404 Not Found", expected);
    }
}