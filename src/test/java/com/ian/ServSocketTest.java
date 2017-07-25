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
        String response = ClientSocketHelper.request("GET / HTTP/1.1");
        assertEquals("HTTP/1.1 200 OK", response);
    }

    @Test
    public void testClientSocketGetsHTTP404ResponseWhenAnythingOtherThanRootIsRequested() {
        String response = ClientSocketHelper.request("GET /a-day-not-night-to-see-till-i-see-thee HTTP/1.1");
        assertEquals("HTTP/1.1 404 Not Found", response);
    }
}