package com.ian;

import java.util.HashMap;

public class RequestValidator {
    public static byte[] validate(String directory, String request) {
        HashMap<String, String> parsed = Parser.parse(request);
        if (badRequest(parsed.get("method"), parsed.get("url"), parsed.get("httpVersion"))) {
            return DisembodiedSousChef.craft400Response();
        } else if (notFound(directory, parsed.get("url"))) {
            return order404Response(directory, parsed.get("url"), parsed.get("urlParams"));
        } else if (AuthValidator.unauthorized(parsed.get("url"), parsed.get("headers"))) {
            return DisembodiedSousChef.craft401Response();
        } else if (RangeValidator.notInRange(directory, parsed.get("url"), parsed.get("headers"))) {
            return DisembodiedSousChef.craft416Response(directory, parsed.get("url"));
        } else if (RangeValidator.itIsARangeRequest(parsed.get("headers"))) {
            return order206Response(directory, parsed.get("url"), parsed.get("headers"));
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
        return Kitchen.sendOrderToChef(directory, url, ParamsParser.parseParams(params));
    }

    public static byte[] order206Response(String directory, String url, String unParsedHeaders) {
        HashMap<String, String> headers = HeadersParser.parseHeaders(unParsedHeaders);
        long[] range = RangeHeaderParser.parse(directory, url, headers.get("Range"));
        return Kitchen.sendPartialOrderToChef(directory, url, range[0], range[1]);
    }
}
