package com.ian;

public class DisembodiedSousChef {
    public static byte[] craftResponseHead(String directory, String request, byte[] body) {
        byte[] responseLines = craftResponseLines(directory, request);
        byte[] standardHeaders = HeadersCook.craftStandardHeaders(directory, request, body);
        byte[] crlf = "\r\n\r\n".getBytes();
        byte[][] responseHead = { responseLines, standardHeaders, crlf };
        return ByteArrayCook.concatenateByteArrays(responseHead);
    }

    public static byte[] craftResponseLines(String directory, String request) {
        if (RedirectLister.checkRedirect(request)) {
            return RedirectCook.craft302Response(request);
        } else if (HTCPCPLister.checkTea(request)) {
            return SuccessCook.craft2XXResponse();
        } else if (HTCPCPLister.checkCoffee(request)) {
            return ClientErrorCook.craft418Response();
        } else if (FileStocker.inStock(directory, request)) {
            return SuccessCook.craft2XXResponse();
        } else {
            return ClientErrorCook.craft404Response();
        }
    }
}
