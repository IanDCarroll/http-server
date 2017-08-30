package com.ian;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileFridge {


    public static boolean inStock(String directory, String name) {
        File file = new File(directory, name);
        return file.exists();
    }

    public static boolean isBox(String directory, String name) {
        File file = new File(directory, name);
        return file.isDirectory();
    }

    public static byte[] pullBytes(String directory, String name) {
        File file = new File(directory, name);
        byte[] bytes = {0};
        try {
            bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) { System.out.println(); }
        return bytes;
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
