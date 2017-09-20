package com.ian;

public class ResponseChef {
    public static byte[] craftResponse(String directory, String order, String[] params) {
        byte[] body = CorpulentSousChef.craftResponseBody(directory, order, params);
        byte[] head = DisembodiedSousChef.craftResponseHead(directory, order, body);
        byte[][] response = {head, body};
        return ByteArrayCook.concatenateByteArrays(response);
    }

    public static byte[] craftPartialResponse(String directory, String order, long start, long end) {
        byte[] body = CorpulentSousChef.craftPartialBody(directory, order, start, end);
        byte[] head = DisembodiedSousChef.craft206Response(directory, order, body, start, end);
        byte[][] response = {head, body};
        return ByteArrayCook.concatenateByteArrays(response);
    }
}