package com.ian;

import java.io.File;

public class HeadersChef {
    public static byte[] plateHeaders(String directory, String name) {
        String contentLength = plateContentLength(FileFridge.size(directory, name));
        String contentType = plateContentType(FileFridge.type(directory, name));
        String headers = contentLength + contentType;
        return headers.getBytes();
    }

    public static String plateContentLength(long bytes) {
        return "\nContent-Length: " + String.valueOf(bytes);
    }

    public static String plateContentType(String mediaType) {
        return "\nContent-Type: " + mediaType;
    }
}
