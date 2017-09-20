package com.ian;

import org.junit.Test;
import static org.junit.Assert.*;

public class ContentCookTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void craftContentsReturnsTheBodyFoundInAFile() {
        //GIVEN
        byte[] expected = "file2 contents\n".getBytes();
        String request = "/file2";
        //WHEN
        byte[] actual = ContentCook.craftContents(directory, request);
        //THEN
        assertEquals(new String(expected), new String(actual));
    }
}