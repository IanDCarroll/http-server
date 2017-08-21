package com.ian;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Chef {
    private static final String crlf = "\r\n\r\n";
    private static final String nl = "\n";
    private static final String header = "Content-Type: text/plain";
    private static final String ok = "HTTP/1.1 200 OK";
    private static final String notFound = "HTTP/1.1 404 Not Found";

    public static String plate(String directory, String order) {
        File entree = new File(directory, order);
        if (!entree.exists()) {
            return notFound;
        } else if (entree.isDirectory()) {
            return menuDuJour(entree);
        } else {
            return cookOrder(entree);
        }
    }

    public static String menuDuJour(File directory) {
        String newLine = "\n";
        String[] entrees = directory.list();
        for (int i = 0; i < entrees.length; i++) {
            entrees[i] = "<a href=\"" + entrees[i] + "\">" + entrees[i] + "</a>";
        }
        String menu = String.join(newLine, entrees);
        return ok + crlf + menu;
    }

    public static String cookOrder(File entree) {
        String voila;
        try {
            byte[] raw_ingredients = Files.readAllBytes(Paths.get(entree.getAbsolutePath()));
            String sautee = new String(raw_ingredients);
            voila = ok + Souchef.plateHeaders() + crlf + sautee;
        } catch (IOException e) { voila = e.getMessage(); }
        return voila;
    }
}
