package com.ian;

import java.util.HashMap;

public class RequestValidator {
    public static byte[] validate(String directory, String request) {
        HashMap<String, String> parsed = Parser.parse(request);
        String method = parsed.get("method");
        String url = parsed.get("url");
        String httpV = parsed.get("httpVersion");
        String urlParams = parsed.get("urlParams");
        String allHeaders = parsed.get("headers");
        if (badRequest(method, url, httpV)) {
            return DisembodiedSousChef.craft400Response();
        } else if (notFound(directory, url)) {
            return order404Response(directory, url, urlParams);
        } else if (AuthValidator.unauthorized(url, allHeaders)) {
            return DisembodiedSousChef.craft401Response();
        } else if (RangeValidator.notInRange(directory, url, allHeaders)) {
            return DisembodiedSousChef.craft416Response(directory, url);
        } else if (RangeValidator.itIsARangeRequest(allHeaders)) {
            return order206Response(directory, url, allHeaders);
        }
        return Methodizer.takeOrder(directory, request);
    }

    public static boolean badRequest(String method, String url, String httpVersion) {
        return method.isEmpty() || url.isEmpty() || httpVersion.isEmpty();
    }

    public static boolean notFound(String directory, String url) {
        return !FileStocker.inStock(directory, url);
    }

    public static byte[] order404Response(String directory, String url, String params) {
        return ResponseChef.craftResponse(directory, url, ParamsParser.parseParams(params));
    }

    public static byte[] order206Response(String directory, String url, String unParsedHeaders) {
        HashMap<String, String> headers = HeadersParser.parseHeaders(unParsedHeaders);
        String rangeValues = headers.get("Range");
        long[] range = RangeHeaderParser.parse(directory, url, rangeValues);
        return ResponseChef.craftPartialResponse(directory, url, range[0], range[1]);
    }
}
