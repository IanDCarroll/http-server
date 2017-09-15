package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class KitchenTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void sendOrderToChefGets200ResponseFromChef() {
        String url = "/";
        String[] parsedParams = {"name=Sir Galahad of Camelot",
                "quest=To Seek The Holy Grail",
                "favorite-color=blue. No. Yelloooooow..."};
        String expected = "HTTP/1.1 200 OK";
        byte[] actual = Kitchen.sendOrderToChef(directory, url, parsedParams);
        assertTrue(new String(actual).contains(expected));
    }

    @Test
    public void sendOrderToChefGetsParamsInResponseFromChef() {
        String url = "/";
        String[] parsedParams = {"name=Sir Galahad of Camelot",
                           "quest=To Seek The Holy Grail",
                           "favorite-color=blue. No. Yelloooooow..."};
        String expected = "<p>name = Sir Galahad of Camelot</p>\n" +
                "<p>quest = To Seek The Holy Grail</p>\n" +
                "<p>favorite-color = blue. No. Yelloooooow...</p>\n";
        byte[] actual = Kitchen.sendOrderToChef(directory, url, parsedParams);
        assertTrue(new String(actual).contains(expected));
    }

    @Test
    public void sendOrderToChefDoesntMindIfNoParamsAreSent() {
        String url = "/";;
        String expected = "HTTP/1.1 200 OK";
        byte[] actual = Kitchen.sendOrderToChef(directory, url);
        assertTrue(new String(actual).contains(expected));
    }

    @Test
    public void sendOrderToSousChefGetsAWellCraftedResponseHead() {
        String url = "/";
        String expected = "HTTP/1.1 200 OK\n" +
                "Content-Length: 0\n" +
                "Content-Type: text/html" +
                "\r\n\r\n";
        byte[] actual = Kitchen.sendOrderToSousChef(directory, url);
        assertEquals(new String(actual), expected);
    }
}