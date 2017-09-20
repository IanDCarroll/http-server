package com.ian;

public class RangeHeaderParser {
    public static long[] parse(String directory, String url, String rangeValue) {
        String[] range = splitEndsOf(firstRange(andRemoveWordsFrom(rangeValue)));
        long start = Long.parseLong(range[0]);
        long end = range[1].isEmpty() ? lastIndex(directory, url) : Long.parseLong(range[1]);
        return new long[] { start, end };
    }

    public static String andRemoveWordsFrom(String rangeValue) {
        String wordChars = "[A-Za-z]*=*";
        String withEmptiness = "";
        return rangeValue.replaceAll(wordChars, withEmptiness);
    }

    public static String firstRange(String wordlessValue) {
        String byCommaAndSpace = ", ";
        return wordlessValue.split(byCommaAndSpace)[0];
    }

    public static String[] splitEndsOf(String oneWordlessRangePair) {
        String byDash = "-";
        String[] basePair = oneWordlessRangePair.trim().split(byDash);
        String start = basePair[0].isEmpty() ? "0" : basePair[0];
        String end = basePair.length==1 ? "" : basePair[1];
        return new String[] { start, end };
    }

    public static long lastIndex(String directory, String url) {
        return FileStocker.size(directory, url)-1;
    }
}
