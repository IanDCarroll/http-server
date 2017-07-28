package com.ian;

import static org.junit.Assert.assertEquals;
import org.junit.*;

public class IntegrationTest {
    public static Server server = new Server();
    public static Runnable serverRunnable = () -> server.serve();
    public static Thread serverThread = new Thread(serverRunnable);

    @BeforeClass
    public static void startUp() {
        serverThread.start();

        synchronized (server) {
            try {
                server.wait();
            } catch (InterruptedException e) { e.getMessage(); }
        }
    }

    @AfterClass
    public static void tearDown() {
        server.close();
    }

    @Test
    public void testClientSocketGetsHTTP200ResponseWhenRootIsRequested() {
        String response = ClientHelper.request("GET / HTTP/1.1");
        assertEquals("HTTP/1.1 200 OK", response);
    }

    @Test
    public void testClientSocketGetsHTTP404ResponseWhenAnythingOtherThanRootIsRequested() {
        String response = ClientHelper.request("GET /a-day-not-night-to-see-till-i-see-thee HTTP/1.1");
        assertEquals("HTTP/1.1 404 Not Found", response);
    }
}