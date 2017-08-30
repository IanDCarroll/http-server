package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileFridgeTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void inStockReturnsTrueIfTheFileExists() {
        boolean expected = true;
        boolean actual = FileFridge.inStock(directory, "/file1");
        assertEquals(expected, actual);
    }

    @Test
    public void inStockReturnsFalseIfTheFileDoesNotExist() {
        boolean expected = false;
        boolean actual = FileFridge.inStock(directory, "/the-lost-city-of-el-dorado");
        assertEquals(expected, actual);
    }

    @Test
    public void isBoxReturnsTrueIfTheFileIsADirectory() {
        boolean expected = true;
        boolean actual = FileFridge.isBox(directory, "/");
        assertEquals(expected, actual);
    }

    @Test
    public void isBoxReturnsFalseIfTheFileIsNotADirectory() {
        boolean expected = false;
        boolean actual = FileFridge.isBox(directory, "/file1");
        assertEquals(expected, actual);
    }

    @Test
    public void pullBytesReturnsAByteArrayOfTheFileContents() {
        byte[] expected = "file1 contents".getBytes();
        byte[] actual = FileFridge.pullBytes(directory, "/file1");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void sizeReturnsTheNUmberOfBytesTheFIleContains() {
        long expected = 14;
        long actual = FileFridge.size(directory, "/file1");
        assertEquals(expected, actual);
    }

    @Test
    public void typeReturnsTheMediaTypeOfTheFile() {
        String text = "text/plain";
        String jpeg = "image/jpeg";
        String gif = "image/gif";
        String png = "image/png";
        String actualText = FileFridge.type(directory, "/file1");
        String actualJpeg = FileFridge.type(directory, "/image.jpeg");
        String actualGif = FileFridge.type(directory, "/image.gif");
        String actualPng = FileFridge.type(directory, "/image.png");
        assertEquals(text, actualText);
        assertEquals(jpeg, actualJpeg);
        assertEquals(gif, actualGif);
        assertEquals(png, actualPng);
    }
}