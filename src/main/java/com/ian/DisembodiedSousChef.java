package com.ian;

public class DisembodiedSousChef {
    public static byte[] craftResponseHead(String directory, String request, byte[] body) {
        byte[] startLine = craftStartLine(directory, request);
        byte[] headers = HeadersCook.craftHeaders(directory, request, body);
        byte[] crlf = "\r\n\r\n".getBytes();
        byte[][] responseHead = { startLine, headers, crlf };
        return ByteArrayCook.concatenateByteArrays(responseHead);
    }

    public static byte[] craftStartLine(String directory, String request) {
        if (HTCPCPChecker.checkTea(request)) {
            return SuccessCook.craft2XXResponse();
        } else if (HTCPCPChecker.checkCoffee(request)) {
            return ClientErrorCook.craft418Response();
        } else if (FileStocker.inStock(directory, request)) {
            return SuccessCook.craft2XXResponse();
        } else {
            return ClientErrorCook.craft404Response();
        }
    }
}
