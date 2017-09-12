package com.ian;

public class CorpulentSousChef {
    private static byte[] personalApology = ("This is the Corpulent Sous Chef.\n" +
            "on behalf of our Cafe, " +
            "I must personally apologize for not having your request on the menu.\n" +
            "It is our deepest regret we could not serve you this thing.").getBytes();
    private static byte[] HTCPCPResponse = "I'm a teapot.".getBytes();

    public static byte[] craftResponseBody(String directory, String request, String[] params) {
        byte[] responseBody;
        if (HTCPCPChecker.check(request)) {
            responseBody = HTCPCPResponse; }
        else if (!FileStocker.inStock(directory, request)) {
            responseBody = personallyApologize(params);
        } else if (FileStocker.isBox(directory, request)) {
            responseBody = DirectoryCook.craftResponseLinks(directory, request, params);
        } else {
            responseBody = ContentCook.craftContents(directory, request);
        }
        return responseBody;
    }

    public static byte[] personallyApologize(String[] params) {
        return params.length == 0 ? personalApology : apologyPlus(params);
    }

    public static byte[] apologyPlus(String[] params) {
        byte[] paramBytes = ParamsCook.craftParams(
                ParamParser.expandAssignmentOperators(params));
        byte[][] allApologies = { personalApology, paramBytes };
        return ByteArrayCook.concatenateByteArrays(allApologies);
    }
}
