package com.ian;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileFridge {

    public static boolean waste(String directory, String name) {
        boolean success = false;
        File file = new File(directory, name);
        if (file.exists()) { success = file.delete(); }
        return success;
    }

    public static boolean stock(String directory, String name) {
        byte[] makeAnEmptyFile = {0};
        pushBytes(directory, name, makeAnEmptyFile);
        return inStock(directory, name);
    }

    public static boolean inStock(String directory, String name) {
        File file = new File(directory, name);
        return file.exists();
    }

    public static void pushBytes(String directory, String name, byte[] toBeWritten) {
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

    public static byte[] pullBytes(String directory, String name) {
        byte[] bytes = {0};
        try {
            bytes = Files.readAllBytes(Paths.get(directory + name));
        } catch (IOException e) { System.out.println(); }
        return bytes;
    }

    public static boolean isBox(String directory, String name) {
        File file = new File(directory, name);
        return file.isDirectory();
    }

    public static long size(String directory, String name) {
        File file = new File(directory, name);
        return file.length();
    }

    public static String type(String directory, String name) {
        String byFileTypeDelimiter = "\\.";
        File file = new File(directory, name);
        String type = "text/plain";
        String fileName = file.getName();
        String[] fileParts = fileName.split(byFileTypeDelimiter);
        String lastPart = fileParts[fileParts.length -1];
        if (lastPart.equals("jpeg")) {
            type = "image/jpeg";
        } else if (lastPart.equals("gif")) {
            type = "image/gif";
        } else if (lastPart.equals("png")) {
            type = "image/png";
        }
        return type;
    }
}
