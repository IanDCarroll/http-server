package com.ian;

import java.util.HashMap;

public class RangeValidator {
    public static boolean notInRange(String directory, String url, String unParsedHeaders) {
        if (itIsARangeRequest(unParsedHeaders)) {
            HashMap<String, String> headers = HeadersParser.parseHeaders(unParsedHeaders);
            String range = headers.get("Range");
            long [] values = RangeHeaderParser.parse(directory, url, range);
            long size = FileStocker.size(directory, url);
            return itIsNotInBytes(range) || rangesAreWrong(values[0], values[1], size);
        }
        return false;
    }

    public static boolean itIsARangeRequest(String unParsedHeaders) {
        return unParsedHeaders.contains("Range: ");
    }

    public static boolean itIsNotInBytes(String rangeHeader) {
        return !rangeHeader.contains("bytes");
    }

    public static boolean rangesAreWrong(long start, long end, long size) {
        return start > end || end > size;
    }
}
