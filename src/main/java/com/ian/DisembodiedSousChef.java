package com.ian;

public class DisembodiedSousChef {
    public static byte[] craftResponseHead(String directory, String request) {
        byte[] startLine = craftStartLine(directory, request);
        byte[] headers = HeadersCook.craftHeaders(directory, request);
        byte[] crlf = "\r\n\r\n".getBytes();
        byte[][] responseHead = { startLine, headers, crlf };
        return ByteArrayCook.concatenateByteArrays(responseHead);
    }

    public static byte[] craftStartLine(String directory, String request) {
        return FileStocker.inStock(directory, request) ?
                SuccessCook.craft2XXResponse() :
                ClientErrorCook.craft4XXResponse() ;
    }
}
