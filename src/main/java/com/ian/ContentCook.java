package com.ian;

public class ContentCook {
    public static byte[] craftContents(String directory, String request) {
        return FileStocker.pullBytes(directory, request);
    }
}
