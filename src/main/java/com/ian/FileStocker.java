package com.ian;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileStocker {

    public static void deleteBytes(String directory, String name) {
        boolean overwrite = false;
        byte[] noBytes = "".getBytes();
        if (inStock(directory, name)) {
            writeToFile(directory, name, noBytes, overwrite);
        }
    }

    public static boolean inStock(String directory, String name) {
        File file = new File(directory, name);
        return file.exists();
    }

    public static void pushBytes(String directory, String name, byte[] toBeWritten) {
        boolean append = true;
        writeToFile(directory, name, toBeWritten, append);
    }

    public static void writeToFile(String directory, String name, byte[] toBeWritten, boolean append) {
        File file = new File(directory, name);
        try {
            FileOutputStream outToFile = new FileOutputStream(file, append);
            try {
                outToFile.write(toBeWritten);
                outToFile.close();
            } catch (IOException e) { System.out.println( e.getMessage()); }
        } catch (FileNotFoundException e) { System.out.println( e.getMessage()); }
    }

    public static byte[] pullRange(String directory, String name, long start, long end) {
        byte[] fullValue = pullBytes(directory, name);
        int endNonInclusive = (int)end + 1;
        return Arrays.copyOfRange(fullValue,(int)start, endNonInclusive);
    }

    public static byte[] pullBytes(String directory, String name) {
        byte[] bytes = {0};
        try {
            bytes = Files.readAllBytes(Paths.get(directory + name));
        } catch (IOException e) { System.out.println(); }
        return bytes;
    }

    public static String[] stockList(String directory, String name) {
        File file = new File(directory, name);
        return file.list();
    }

    public static boolean isDir(String directory, String name) {
        File file = new File(directory, name);
        return file.isDirectory();
    }

    public static long size(String directory, String name) {
        File file = new File(directory, name);
        return file.length();
    }
}
