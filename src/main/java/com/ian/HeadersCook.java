package com.ian;

public class HeadersCook {
    public static byte[] craftStandardHeaders(String directory, String name, byte[] body) {
        String contentLength = craftContentLength(body);
        String contentType = craftContentType(directory, name);
        return (contentLength + contentType).getBytes();
    }

    public static byte[] craftPartialHeaders(String directory, String name, byte[] body, long start, long end) {
        String contentRange  = craftContentRange(directory, name, start, end);
        String contentLength = craftContentLength(body);
        return (contentRange + contentLength).getBytes();
    }

    public static String craftContentLength(byte[] body) {
        return "\nContent-Length: " + String.valueOf(body.length);
    }

    public static String craftContentType(String directory, String name) {
        return "\nContent-Type: " + type(directory, name);
    }

    public static String craftContentRange(String directory, String name, long start, long end) {
        return "\nContent-Range: bytes " +
                String.valueOf(start) + "-" +
                String.valueOf(end) + "/" +
                String.valueOf(FileStocker.size(directory, name));
    }

    public static String craftLocation(String url) {
        return "\nLocation: " + RedirectLister.getRedirectionUrl(url);
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
