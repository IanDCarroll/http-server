package com.ian;

public class ParamsCook {
    public static byte[] craftParams(String[] params) {
        String delimiter = "";
        return String.join(delimiter, params).getBytes();
    }
}
