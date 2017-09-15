package com.ian;

import java.util.Arrays;
import java.util.stream.Stream;

public class DirectoryCook {
    private static String HTMLBeginning = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<title></title>\n" +
            "</head>\n" +
            "<body>\n";
    private static String HTMLEnding = "\n</body>\n</html>";
    private static String[] HTMLConstants = { HTMLBeginning, HTMLEnding};

    public static byte[] craftResponseLinks(String directory, String request, String[] params) {
        String links = joinLinks(directory, request, params);
        return String.join(links, HTMLConstants).getBytes();
    }

    public static String joinLinks(String directory, String request, String[] params) {
        String newLine = "\n";
        return String.join(newLine, addFilesAndParams(directory, request, params));
    }

    public static String[] addFilesAndParams(String directory, String request, String[] params) {
        String[] files = linkifyFiles(directory, request);
        params = formatParams(params);
        return Stream.concat(Arrays.stream(files), Arrays.stream(params))
                .toArray(String[]::new);
    }

    public static String[] linkifyFiles(String directory, String request) {
        String[] files = FileStocker.stockList(directory, request);
        for (int i = 0; i < files.length; i++) {
            files[i] = linkifyFile(files[i]);
        }
        return files;
    }

    public static String linkifyFile(String file) {
        return "<a href=\"/" + file + "\">" + file + "</a>";
    }

    public static String[]formatParams(String[] params) {
        params = AssignmentParser.expandAssignmentOperators(params);
        for (int i = 0; i < params.length; i++) {
            params[i] = formatParam(params[i]);
        }
        return params;
    }

    public static String formatParam(String param) { return "<p>" + param + "</p>"; }
}
