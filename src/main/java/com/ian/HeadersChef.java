package com.ian;

public class HeadersChef {
    public static byte[] plateHeaders(String directory, String name) {
        String contentLength = plateContentLength(FileFridge.size(directory, name));
        String contentType = plateContentType(FileFridge.type(directory, name));
        String headers = contentLength + contentType;
        return headers.getBytes();
    }

    public static String plateContentLength(long length) {
        return "\nContent-Length: " + String.valueOf(length);
    }

    public static String plateContentType(String mediaType) {
        return "\nContent-Type: " + mediaType;
    }
}
