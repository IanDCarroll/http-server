package com.ian;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Chef {
    private static final byte[] crlf = "\r\n\r\n".getBytes();
    private static final byte[] ok = "HTTP/1.1 200 OK".getBytes();
    private static final byte[] notFound = "HTTP/1.1 404 Not Found".getBytes();

    public static byte[] plate(String directory, String[] order) {
        File entree = new File(directory, order[0]);
        if (!entree.exists()) {
            return notFound;
        } else if (entree.isDirectory()) {
            return menuDuJour(entree);
        } else {
            return cookOrder(entree);
        }
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
        byte[][] allBytes = { ok, crlf, menu };
        return LineCook.marinateBytes(allBytes);
    }

    public static byte[] cookOrder(File entree) {
        byte[] voila = new byte[0];
        try {
            byte[] raw_ingredients = Files.readAllBytes(Paths.get(entree.getAbsolutePath()));
            byte[] headers = SousChef.plateHeaders(entree);
            byte[][] allBytes = { ok, headers, crlf, raw_ingredients };
            voila = LineCook.marinateBytes(allBytes);

        } catch (IOException e) { System.out.println(e.getMessage()); }

        return voila;
    }
}
