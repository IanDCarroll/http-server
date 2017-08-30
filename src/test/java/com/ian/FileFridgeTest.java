package com.ian;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    public void pushByteWritesAByteArrayToAnEmptyFile() {
        String fileName = "/empty-file";
        byte[] toBeWritten = "lacuna".getBytes();
        FileFridge.pushBytes(directory, fileName, toBeWritten);
        byte[] expected = toBeWritten;
        byte[] actual = getFileBytes(fileName);
        ensureDeletion(fileName);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void pushBytesAppendsAByteArrayToTheEndOfTheFile() {
        String fileName = "/the-argument-you-lost-half-an-hour-ago";
        String startingContents = "Nah-aah!\nYes-huh!\nNah-ahh!\nYES-HUH!\n";
        String toBeAppended = "And another thing...";
        byte[] expected = (startingContents + toBeAppended).getBytes();

        setFileBytes(fileName, startingContents.getBytes());
        FileFridge.pushBytes(directory, fileName, toBeAppended.getBytes());
        byte[] actual = getFileBytes(fileName);

        ensureDeletion(fileName);
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


    @Test
    public void stockCreatesANewFile() {
        String fileName = "/canned-salmon";
        boolean expected = true;
        boolean actual = FileFridge.stock(directory, fileName);
        ensureDeletion(fileName);
        assertEquals(expected, actual);
    }

    @Test
    public void wasteDeletesAFile() {
        String fileName = "/the-salmon-mousse";
        boolean expected = true;
        byte[] empty = {0};
        setFileBytes(fileName, empty);
        boolean actual = FileFridge.waste(directory, fileName);
        ensureDeletion(fileName);
        assertEquals(expected, actual);
    }

    public static byte[] getFileBytes(String name) {
        byte[] bytes = {0};
        try {
            bytes = Files.readAllBytes(Paths.get(directory + name));
        } catch (IOException e) { System.out.println(e.getMessage()); }
        return bytes;
    }

    public static void setFileBytes(String name, byte[] toBeWritten) {
        boolean append = true;
        File file = new File(directory, name);
        try {
            FileOutputStream outToFile = new FileOutputStream(file, append);
            try {
                outToFile.write(toBeWritten);
                outToFile.close();
            } catch (IOException e) { System.out.println( e.getMessage()); }
        } catch (FileNotFoundException e) { System.out.println( e.getMessage()); }
    }

    public static void ensureDeletion(String name) {
        File file = new File(directory, name);
        file.delete();
    }
}