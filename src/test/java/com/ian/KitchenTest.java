package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class KitchenTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void sendOrderToChefGets200ResponseFromChef() {
        //GIVEN
        String expected = "HTTP/1.1 200 OK";
        String url = "/";
        String[] parsedParams = {"name=Sir Galahad of Camelot",
                "quest=To Seek The Holy Grail",
                "favorite-color=blue. No. Yelloooooow..."};
        //WHEN
        byte[] actual = Kitchen.sendOrderToChef(directory, url, parsedParams);
        //THEN
        assertTrue(new String(actual).contains(expected));
    }

    @Test
    public void sendOrderToChefGetsParamsInResponseFromChef() {
        //GIVEN
        String expected = "<p>name = Sir Galahad of Camelot</p>\n" +
                "<p>quest = To Seek The Holy Grail</p>\n" +
                "<p>favorite-color = blue. No. Yelloooooow...</p>\n";
        String url = "/";
        String[] parsedParams = {"name=Sir Galahad of Camelot",
                           "quest=To Seek The Holy Grail",
                           "favorite-color=blue. No. Yelloooooow..."};
        //WHEN
        byte[] actual = Kitchen.sendOrderToChef(directory, url, parsedParams);
        //THEN
        assertTrue(new String(actual).contains(expected));
    }

    @Test
    public void sendOrderToChefDoesntMindIfNoParamsAreSent() {
        //GIVEN
        String expected = "HTTP/1.1 200 OK";
        String url = "/";
        //WHEN
        byte[] actual = Kitchen.sendOrderToChef(directory, url);
        //THEN
        assertTrue(new String(actual).contains(expected));
    }

    @Test
    public void sendOrderToSousChefGetsAWellCraftedResponseHead() {
        //GIVEN
        String expected = "HTTP/1.1 200 OK\n" +
                "Content-Length: 0\n" +
                "Content-Type: text/html" +
                "\r\n\r\n";
        String url = "/";
        //WHEN
        byte[] actual = Kitchen.sendOrderToSousChef(directory, url);
        //THEN
        assertEquals(new String(actual), expected);
    }
}