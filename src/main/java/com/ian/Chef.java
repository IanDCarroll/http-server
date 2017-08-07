package com.ian;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Chef {
    private static final String crlf = "\r\n\r\n";
    private static final String ok = "HTTP/1.1 200 OK" + crlf;
    private static final String notFound = "HTTP/1.1 404 Not Found";

    public static String plate(String directory, String order) {
        return (order.equals("/")) ? ok : searchMenu(directory, order);
    }

    public static String searchMenu(String directory, String order) {
        File file = new File(directory, order);
        byte [] raw_contents;
        String contents = notFound;


        if (file.exists()) {
            try {
                raw_contents = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
                contents = new String(raw_contents);
                return ok + contents;
            } catch (IOException e) { System.out.println(e.getMessage()); }
        }
        return contents;
    }
}
