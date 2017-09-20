package com.ian;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ServerTest {
    public static final int defaultPort = 5000;
    public static final String defaultDirectory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void serverServesDefaultPortAndDirectory() {
        //GIVEN
            /* no arguments */
        //WHEN
        Server server = new Server();
        //THEN
        assertEquals(defaultPort, server.port);
        assertEquals(defaultDirectory, server.directory);
    }

    @Test
    public void serverServesSpecifiedPortWithDefaultDirectory() {
        //GIVEN
        int specifiedPort = 1701;
        //WHEN
        Server server = new Server(specifiedPort);
        //THEN
        assertEquals(specifiedPort, server.port);
        assertEquals(defaultDirectory, server.directory);
    }

    @Test
    public void serverServesDefaultPortWithSpecifiedDirectory() {
        //GIVEN
        String specifiedDirectory = "/Users/geordie/dr_leah_brahms_sim";
        //WHEN
        Server server = new Server(specifiedDirectory);
        //THEN
        assertEquals(defaultPort, server.port);
        assertEquals(specifiedDirectory, server.directory);
    }

    @Test
    public void serverServesSpecifiedPortAndDirectory() {
        //GIVEN
        int specifiedPort = 1701;
        String specifiedDirectory = "/Users/geordie/dr_leah_brahms_sim";
        //WHEN
        Server server = new Server(specifiedPort, specifiedDirectory);
        //THEN
        assertEquals(specifiedPort, server.port);
        assertEquals(specifiedDirectory, server.directory);
    }
}
