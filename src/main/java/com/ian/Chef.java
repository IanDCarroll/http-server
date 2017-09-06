package com.ian;

public class Chef {
    private static final byte[] crlf = "\r\n\r\n".getBytes();
    private static final byte[] ok = "HTTP/1.1 200 OK".getBytes();
    private static final byte[] notFound = "HTTP/1.1 404 Not Found".getBytes();
    private static final byte[] noBytes = {};
    public static byte[] paramBytes = noBytes;

    public static byte[] plate(String directory, String order, String[] params) {
        paramBytes = ParamsChef.plateParams(params);
        if (!FileFridge.inStock(directory, order)) {
            return notOnTheMenu();
        } else if (FileFridge.isBox(directory, order)) {
            return menuDuJour(directory, order);
        } else {
            return cookOrder(directory, order);
        }
    }

    public static byte[] notOnTheMenu() {
        byte[][] allBytes = { notFound, crlf, paramBytes };
        resetParamBytes();
        return LineCook.marinateBytes(allBytes);
    }

    public static byte[] menuDuJour(String directory, String order) {
        byte[] menu = buildMenu(directory, order);
        byte[][] allBytes = { ok, crlf, menu, paramBytes };
        resetParamBytes();
        return LineCook.marinateBytes(allBytes);
    }

    public static byte[] buildMenu(String directory, String order) {
        String[] menuBacking = buildMenuBacking();
        String menuContent = buildMenuContent(directory, order);
        return String.join(menuContent, menuBacking).getBytes();
    }

    public static String[] buildMenuBacking() {
        String beginning = buildBeginning();
        String end = "\n</body>\n</html>";
        String[] backing = {beginning, end};
        return backing;
    }

    public static String buildBeginning() {
        String beginning = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title></title>\n" +
                "</head>\n" +
                "<body>\n";
        String params = new String(paramBytes);
        String paramsParagraph = "<p>" + params + "</p>";
        return params.equals("") ? beginning : beginning + paramsParagraph;
    }

    public static String buildMenuContent(String directory, String order) {
        String newLine = "\n";
        String[] entrees = FileFridge.stockList(directory, order);
        for (int i = 0; i < entrees.length; i++) {
            entrees[i] = "<a href=\"/" + entrees[i] + "\">" + entrees[i] + "</a>";
        }
        return String.join(newLine, entrees);
    }

    public static byte[] cookOrder(String directory, String order) {
        byte[] raw_ingredients = FileFridge.pullBytes(directory, order);
        byte[] headers = HeadersChef.plateHeaders(directory, order);
        byte[][] allBytes = { ok, headers, crlf, raw_ingredients };
        byte[] voila = LineCook.marinateBytes(allBytes);
        resetParamBytes();
        return voila;
    }

    public static void resetParamBytes() {
        paramBytes = noBytes;
    }
}
