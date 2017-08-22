package com.ian;

import java.io.File;

public class SousChef {
    public static byte[] plateHeaders(File file) {
        String contentLength = plateContentLength(file.length());
        String contentType = plateContentType(file.getName());
        String headers = contentLength + contentType;
        return headers.getBytes();

    }

    public static String plateContentLength(long bytes) {
        return "\nContent-Length: " + String.valueOf(bytes);
    }

    public static String plateContentType(String fileName) {
        String type = "text/plain";
        String[] fileParts = fileName.split("\\.");
        String lastPart = fileParts[fileParts.length - 1];
        if (lastPart.equals("gif")) {
            type = "image/gif";
        } else if (lastPart.equals("jpeg")) {
            type = "image/jpeg";
        } else if (lastPart.equals("png")) {
            type = "image/png";
        }
        return "\nContent-Type: " + type;
    }
}
