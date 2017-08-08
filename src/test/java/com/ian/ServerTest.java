package com.ian;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ServerTest {
    public static final int defaultPort = 5000;
    public static final String defaultDirectory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void serverServesDefaultPortAndDirectory() {
        Server server = new Server();
        assertEquals(defaultPort, server.port);
        assertEquals(defaultDirectory, server.directory);
    }

    @Test
    public void serverServesSpecifiedPortWithDefaultDirectory() {
        int specifiedPort = 1701;
        Server server = new Server(specifiedPort);
        assertEquals(specifiedPort, server.port);
        assertEquals(defaultDirectory, server.directory);
    }

    @Test
    public void serverServesDefaultPortWithSpecifiedDirectory() {
        String specifiedDirectory = "/Users/geordie/dr_leah_brahms_sim";
        Server server = new Server(specifiedDirectory);
        assertEquals(defaultPort, server.port);
        assertEquals(specifiedDirectory, server.directory);
    }

    @Test
    public void serverServesSpecifiedPortAndDirectory() {
        int specifiedPort = 1701;
        String specifiedDirectory = "/Users/geordie/dr_leah_brahms_sim";
        Server server = new Server(specifiedPort, specifiedDirectory);
        assertEquals(specifiedPort, server.port);
        assertEquals(specifiedDirectory, server.directory);
    }

    @Test
    public void reportReportsCorrectPortAndDirectory() {
        int port = 2525;
        String directory = "/Users/Zager&Evans";
        String expected = "Serving /Users/Zager&Evans on port 2525";
        Server server = new Server(port, directory);
        assertEquals(expected, server.report());
    }
}
