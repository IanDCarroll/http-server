package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileStockerTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void inStockReturnsTrueIfTheFileExists() {
        boolean expected = true;
        boolean actual = FileStocker.inStock(directory, "/file1");
        assertEquals(expected, actual);
    }

    @Test
    public void inStockReturnsFalseIfTheFileDoesNotExist() {
        boolean expected = false;
        boolean actual = FileStocker.inStock(directory, "/the-lost-city-of-el-dorado");
        assertEquals(expected, actual);
    }

    @Test
    public void isBoxReturnsTrueIfTheFileIsADirectory() {
        boolean expected = true;
        boolean actual = FileStocker.isDir(directory, "/");
        assertEquals(expected, actual);
    }

    @Test
    public void isBoxReturnsFalseIfTheFileIsNotADirectory() {
        boolean expected = false;
        boolean actual = FileStocker.isDir(directory, "/file1");
        assertEquals(expected, actual);
    }

    @Test
    public void pullBytesReturnsAByteArrayOfTheFileContents() {
        byte[] expected = "file1 contents".getBytes();
        byte[] actual = FileStocker.pullBytes(directory, "/file1");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void pushByteWritesAByteArrayToAnEmptyFile() {
        String fileName = "/empty-file";
        byte[] toBeWritten = "lacuna".getBytes();
        FileStocker.pushBytes(directory, fileName, toBeWritten);
        byte[] expected = toBeWritten;
        byte[] actual = FileHelper.getFileBytes(directory, fileName);
        FileHelper.ensureDeletion(directory, fileName);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void pushBytesAppendsAByteArrayToTheEndOfTheFile() {
        String fileName = "/the-argument-you-lost-half-an-hour-ago";
        String startingContents = "Nah-aah!\nYes-huh!\nNah-ahh!\nYES-HUH!\n";
        String toBeAppended = "And another thing...";
        byte[] expected = (startingContents + toBeAppended).getBytes();

        FileHelper.setFileBytes(directory, fileName, startingContents.getBytes());
        FileStocker.pushBytes(directory, fileName, toBeAppended.getBytes());
        byte[] actual = FileHelper.getFileBytes(directory, fileName);

        FileHelper.ensureDeletion(directory, fileName);
        assertArrayEquals(expected, actual);
    }
    @Test
    public void sizeReturnsTheNUmberOfBytesTheFIleContains() {
        long expected = 14;
        long actual = FileStocker.size(directory, "/file1");
        assertEquals(expected, actual);
    }

    @Test
    public void deleteBytesSetsAFileToZeroBytes() {
        //GIVEN
        String fileName = "/the-salmon-mousse";
        byte[] expected = "".getBytes();
        byte[] content = "this is content".getBytes();
        FileHelper.setFileBytes(directory, fileName, content);
        //WHEN
        FileStocker.deleteBytes(directory, fileName);
        byte[] actual = FileHelper.getFileBytes(directory, fileName);
        //THEN
        FileHelper.ensureDeletion(directory, fileName);
        assertEquals(new String(expected), new String(actual));
    }
}