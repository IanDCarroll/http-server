package com.ian;

import java.util.HashMap;

public class Methodizer {
    public static String directory = "";

    public static byte[] takeOrder(String directoryToReference, String request) {
        directory = directoryToReference;
        //validator goes here
        HashMap<String, String> parsedRequest = Parser.parse(request);
        String method = parsedRequest.get("method");
        String url = parsedRequest.get("url");
        String[] parsedParams = ParamsParser.parseParams(parsedRequest.get("urlParams"));
        String body = parsedRequest.get("body");
        byte[] response = null;
        if (method.equals("DELETE")) {
            response = Method.delete(directory, url);
        } else if (method.equals("GET")) {
            response = Method.get(directory, url, parsedParams);
        } else if (method.equals("HEAD")) {
            response = Method.head(directory, url);
        } else if (method.equals("POST")) {
            response = Method.post(directory, url, body, parsedParams);
        } else if (method.equals("PUT")) {
            response = Method.put(directory, url, body, parsedParams);
        }
        return response;
    }
}
