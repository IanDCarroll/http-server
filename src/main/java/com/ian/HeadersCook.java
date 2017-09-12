package com.ian;

public class HeadersCook {
    public static byte[] craftHeaders(String directory, String name, byte[] body) {
        String contentLength = craftContentLength(body);
        String contentType = craftContentType(directory, name);
        String headers = contentLength + contentType;
        return headers.getBytes();
    }

    public static String craftContentLength(byte[] body) {
        return "\nContent-Length: " + String.valueOf(body.length);
    }

    public static String craftContentType(String directory, String name) {
        return "\nContent-Type: " + type(directory, name);
    }

    public static String type(String directory, String name) {
        return FileStocker.isDir(directory, name) ? "text/html" : fileType(name);
    }

    public static String fileType(String name) {
        String byFileTypeDelimiter = "\\.";
        String[] fileParts = name.split(byFileTypeDelimiter);
        String lastPart = fileParts[fileParts.length -1];
        String type = "text/plain";
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
