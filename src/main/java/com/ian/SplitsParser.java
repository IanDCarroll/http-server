package com.ian;

public class SplitsParser {
    private static final String emptyString = "";

    public static String[] bisectString(String string, String bySeparationPoint) {
        String firstHalf = string.split(bySeparationPoint)[0];
        String separator = bySeparationPoint.replace("\\", emptyString);
        String secondHalf = string.contains(separator) ?
                string.replace(firstHalf + separator, emptyString) :
                emptyString;
        return new String[] { firstHalf, secondHalf };
    }
}
