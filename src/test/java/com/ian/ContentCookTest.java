package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContentCookTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void craftContentsReturnsTheBodyFoundInAFile() {
        String request = "/file2";
        byte[] expected = "file2 contents\n".getBytes();
        byte[] actual = ContentCook.craftContents(directory, request);
        assertEquals(new String(expected), new String(actual));
    }
}