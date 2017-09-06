package com.ian;

public class ParamsChef {
    public static byte[] plateParams(String[] params) {
        String delimiter = "";
        return String.join(delimiter, params).getBytes();
    }
}
