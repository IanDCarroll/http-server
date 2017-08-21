package com.ian;

import java.io.File;

public class SousChef {
    public static String plateHeaders(File file) {
        String contentLength = plateContentLength(file.length());
        String contentType = plateContentType(file.getName());
        return contentLength + contentType;

    }

    public static String plateContentType(String fileName) {
        String type = "text/plain";
        String[] fileParts = fileName.split("\\.");
        String lastPart = fileParts[fileParts.length - 1];
        if (lastPart.equals("gif")) { type = "image/gif"; }
        return "\nContent-Type: " + type;
    }

    public static String plateContentLength(long bytes) {
        return "\nContent-Length: " + String.valueOf(bytes);
    }
}
