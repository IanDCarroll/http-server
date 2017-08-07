package com.ian;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Chef {
    private static final String crlf = "\r\n\r\n";
    private static final String ok = "HTTP/1.1 200 OK" + crlf;
    private static final String notFound = "HTTP/1.1 404 Not Found";

    public static String plate(String order, String directory) {
        return (order.equals("/")) ? ok : searchMenu(order, directory);
    }

    public static String searchMenu(String uri, String directory) {
        File file = new File(directory, uri);
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
