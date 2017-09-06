package com.ian;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper {
    public static boolean checkExistence(String directory, String name) {
        File file = new File(directory, name);
        return file.exists();
    }

    public static byte[] getFileBytes(String directory, String name) {
        byte[] bytes = {0};
        try {
            bytes = Files.readAllBytes(Paths.get(directory + name));
        } catch (IOException e) { System.out.println(e.getMessage()); }
        return bytes;
    }

    public static void setFileBytes(String directory, String name, byte[] toBeWritten) {
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

    public static void ensureDeletion(String directory, String name) {
        File file = new File(directory, name);
        file.delete();
    }
}
