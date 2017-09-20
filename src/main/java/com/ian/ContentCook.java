package com.ian;

public class ContentCook {
    public static byte[] craftContents(String directory, String request) {
        return FileStocker.pullBytes(directory, request);
    }

    public static byte[] craftPartialContents(String directory, String request, long start, long end) { ;
        return FileStocker.pullRange(directory, request, start, end);
    }
}
