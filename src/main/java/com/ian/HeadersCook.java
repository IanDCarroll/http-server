package com.ian;

public class HeadersCook {
    public static byte[] craftHeaders(String directory, String name) {
        String contentLength = craftContentLength(FileStocker.size(directory, name));
        String contentType = craftContentType(FileStocker.type(directory, name));
        String headers = contentLength + contentType;
        return headers.getBytes();
    }

    public static String craftContentLength(long length) {
        return "\nContent-Length: " + String.valueOf(length);
    }

    public static String craftContentType(String mediaType) {
        return "\nContent-Type: " + mediaType;
    }
}
