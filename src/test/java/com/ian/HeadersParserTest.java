package com.ian;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class HeadersParserTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void parseHeadersParsesABunchOfHeaders() {
        //GIVEN
        String bunchOfheaders = "name: Sir Robin of Camelot\n" +
                "quest: To Seek the Holy Grail\n" +
                "air-speed-velocity of an unladen swallow: What? I don't know that!";
        HashMap<String, String> expected = new HashMap<>();
        expected.put("name", "Sir Robin of Camelot");
        expected.put("quest", "To Seek the Holy Grail");
        expected.put("air-speed-velocity of an unladen swallow", "What? I don't know that!");
        //WHEN
        HashMap<String, String> actual = HeadersParser.parseHeaders(bunchOfheaders);
        //THEN
        assertEquals(expected, actual);
    }
}