package com.ian;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Chef {
    private static final byte[] crlf = "\r\n\r\n".getBytes();
    private static final byte[] ok = "HTTP/1.1 200 OK".getBytes();
    private static final byte[] notFound = "HTTP/1.1 404 Not Found".getBytes();

    public static byte[] plate(String directory, String order) {
        File entree = new File(directory, order);
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
        String dirName = directory.getName();
        String beginning = "<!DOCTYPE html>\n" +
                           "<html>\n" +
                           "<head>\n" +
                           "<title>" + dirName + "</title>\n" +
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

        int firstIndex = 0;
        int headerLength = ok.length + crlf.length;
        int fullLength = ok.length + crlf.length + menu.length;

        byte[] boundMenu = new byte[fullLength];
        System.arraycopy(ok, firstIndex, boundMenu, firstIndex, ok.length);
        System.arraycopy(crlf, firstIndex, boundMenu, ok.length, crlf.length);
        System.arraycopy(menu, firstIndex, boundMenu, headerLength, menu.length);

        return boundMenu;
    }

    public static byte[] cookOrder(File entree) {
        byte[] voila = new byte[0];
        try {
            byte[] raw_ingredients = Files.readAllBytes(Paths.get(entree.getAbsolutePath()));
            byte[] headers = SousChef.plateHeaders(entree);

            int firstIndex = 0;
            int headerLength = ok.length + headers.length;
            int noBodyLength = headerLength + crlf.length;
            int fullLength = ok.length + headers.length + crlf.length + raw_ingredients.length;

            voila = new byte[fullLength];
            System.arraycopy(ok, firstIndex, voila, firstIndex, ok.length);
            System.arraycopy(headers, firstIndex, voila, ok.length, headers.length);
            System.arraycopy(crlf, firstIndex, voila, headerLength, crlf.length);
            System.arraycopy(raw_ingredients, firstIndex, voila, noBodyLength, raw_ingredients.length);
        } catch (IOException e) { System.out.println(e.getMessage()); }

        return voila;
    }
}
