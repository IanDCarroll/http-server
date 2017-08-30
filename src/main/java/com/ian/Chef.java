package com.ian;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Chef {
    private static final byte[] crlf = "\r\n\r\n".getBytes();
    private static final byte[] ok = "HTTP/1.1 200 OK".getBytes();
    private static final byte[] notFound = "HTTP/1.1 404 Not Found".getBytes();
    public static byte[] params = {};

    public static byte[] plate(String directory, String[] order) {
        params = ParamsChef.plateParams(order);
        File entree = new File(directory, order[0]);
        if (!FileFridge.inStock(directory, order[0])) {
            return notOnTheMenu();
        } else if (FileFridge.isBox(directory, order[0])) {
            return menuDuJour(entree);
        } else {
            return cookOrder(directory, order[0]);
        }
    }

    public static byte[] notOnTheMenu() {
        byte[][] allBytes = { notFound, crlf, params };
        resetParams();
        return LineCook.marinateBytes(allBytes);
    }

    public static byte[] menuDuJour(File directory) {
        String newLine = "\n";
        String beginning = "<!DOCTYPE html>\n" +
                           "<html>\n" +
                           "<head>\n" +
                           "<title></title>\n" +
                           "</head>\n" +
                           "<body>\n";
        String end =       "\n</body>" +
                           "\n</html>";
        String[] entrees = directory.list();
        String[] menuBacking = {beginning, end};
        for (int i = 0; i < entrees.length; i++) {
            entrees[i] = "<a href=\"/" + entrees[i] + "\">" + entrees[i] + "</a>";
        }
        String menuContent = String.join(newLine, entrees);
        byte[] menu = String.join(menuContent, menuBacking).getBytes();
        byte[][] allBytes = { ok, crlf, menu, params };
        resetParams();
        return LineCook.marinateBytes(allBytes);
    }

    public static byte[] cookOrder(String directory, String order) {
        byte[] raw_ingredients = FileFridge.pullBytes(directory, order);
        byte[] headers = HeadersChef.plateHeaders(directory, order);
        byte[][] allBytes = { ok, headers, crlf, raw_ingredients, params };
        byte[] voila = LineCook.marinateBytes(allBytes);
        resetParams();
        return voila;
    }

    public static void resetParams() {
        params = new byte[0];
    }
}
