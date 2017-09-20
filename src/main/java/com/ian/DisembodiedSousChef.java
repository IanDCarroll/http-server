package com.ian;

public class DisembodiedSousChef {
    private static final byte[] crlf = "\r\n\r\n".getBytes();

    public static byte[] craftResponseHead(String directory, String request, byte[] body) {
        byte[] responseLines = craftResponseLines(directory, request);
        byte[] standardHeaders = HeadersCook.craftStandardHeaders(directory, request, body);
        byte[][] responseHead = { responseLines, standardHeaders, crlf };
        return ByteArrayCook.concatenateByteArrays(responseHead);
    }

    public static byte[] craftResponseLines(String directory, String request) {
        if (RedirectLister.checkRedirect(request)) {
            return RedirectCook.craft302Response(request);
        } else if (HTCPCPLister.checkTea(request)) {
            return SuccessCook.craft200Response();
        } else if (HTCPCPLister.checkCoffee(request)) {
            return ClientErrorCook.craft418Response();
        } else if (FileStocker.inStock(directory, request)) {
            return SuccessCook.craft200Response();
        } else {
            return ClientErrorCook.craft404Response();
        }
    }

    public static byte[] craft206Response(String directory, String name, byte[] body, long start, long end) {
        byte[] responseLine = SuccessCook.craft206Response();
        byte[] headers = HeadersCook.craftPartialHeaders(directory, name, body, start, end);
        byte[][] response = { responseLine, headers, crlf };
        return ByteArrayCook.concatenateByteArrays(response);
    }

    public static byte[] craft400Response() {
        byte[] responseLine = ClientErrorCook.craft400Response();
        byte[][] response = { responseLine, crlf };
        return ByteArrayCook.concatenateByteArrays(response);
    }

    public static byte[] craft401Response() {
        byte[] responseLine = ClientErrorCook.craft401Response();
        byte[] header = "\nWWW-Authenticate: Basic".getBytes();
        byte[][] response = { responseLine, header, crlf };
        return ByteArrayCook.concatenateByteArrays(response);
    }

    public static byte[] craft416Response(String directory, String url) {
        byte[] responseLine = ClientErrorCook.craft416Response();
        String size = String.valueOf(FileStocker.size(directory, url));
        byte[] header = ("\nContent-Range: */" + size).getBytes();
        byte[][] response = { responseLine, header, crlf };
        return ByteArrayCook.concatenateByteArrays(response);
    }
}
