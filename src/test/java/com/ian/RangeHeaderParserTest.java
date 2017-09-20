package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class RangeHeaderParserTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";
    public static final String url = "/file1";


    @Test
    public void parseParsesARangeHeaderStringToAnArrayOfLongs() {
        long[] expected = { 867, 5309 };
        String rangeValue = "bytes=867-5309";
        long[] actual = RangeHeaderParser.parse(directory, url, rangeValue);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void parseIgnoresRangesAfterTheFirst() {
        long[] expected = { 86, 75 };
        String rangeValue = "bytes=86-75, 3-9";
        long[] actual = RangeHeaderParser.parse(directory, url, rangeValue);
        assertArrayEquals(expected, actual);
    }


    @Test
    public void parseFillsInTheEndWithTheFileLengthIfItsLeftBlank() {
        long[] expected = { 8, 13 };
        String rangeValue = "bytes=8-";
        long[] actual = RangeHeaderParser.parse(directory, url, rangeValue);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void andRemoveWordsFromRemovesWordCharsFromAString() {
        String expected = "86-75, 3-9";
        String rangeValue = "bytes=86-75, 3-9RaNDoJunK";
        String actual = RangeHeaderParser.andRemoveWordsFrom(rangeValue);
        assertEquals(expected, actual);
    }

    @Test
    public void splitEndsOfReturnsALongArrayFromASparseStartingString() {
        String[] expected = { "0", "8675309"};
        String rangeValue = "-8675309";
        String[] actual = RangeHeaderParser.splitEndsOf(rangeValue);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void splitEndsOfReturnsALongArrayFromASparseEndingString() {
        String[] expected = { "7", ""};
        String rangeValue = "7-";
        String[] actual = RangeHeaderParser.splitEndsOf(rangeValue);
        assertArrayEquals(expected, actual);
    }

}