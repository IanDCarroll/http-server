package com.ian;

public class ResponseChef {
    public static byte[] craftResponse(String directory, String order, String[] params) {
        byte[] body = CorpulentSousChef.craftResponseBody(directory, order, params);
        byte[] head = DisembodiedSousChef.craftResponseHead(directory, order, body);
        byte[][] response = {head, body};
        return ByteArrayCook.concatenateByteArrays(response);
    }
}