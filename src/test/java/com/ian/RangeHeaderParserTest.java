package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class RangeHeaderParserTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";
    public static final String url = "/file1";


    @Test
    public void parseParsesARangeHeaderStringToAnArrayOfLongs() {
        //GIVEN
        long[] expected = { 867, 5309 };
        String rangeValue = "bytes=867-5309";
        //WHEN
        long[] actual = RangeHeaderParser.parse(directory, url, rangeValue);
        //THEN
        assertArrayEquals(expected, actual);
    }

    @Test
    public void parseIgnoresRangesAfterTheFirst() {
        //GIVEN
        long[] expected = { 86, 75 };
        String rangeValue = "bytes=86-75, 3-9";
        //WHEN
        long[] actual = RangeHeaderParser.parse(directory, url, rangeValue);
        //THEN
        assertArrayEquals(expected, actual);
    }


    @Test
    public void parseFillsInTheEndWithTheFileLengthIfItsLeftBlank() {
        //GIVEN
        long[] expected = { 8, 13 };
        String rangeValue = "bytes=8-";
        //WHEN
        long[] actual = RangeHeaderParser.parse(directory, url, rangeValue);
        //THEN
        assertArrayEquals(expected, actual);
    }

    @Test
    public void andRemoveWordsFromRemovesWordCharsFromAString() {
        //GIVEN
        String expected = "86-75, 3-9";
        String rangeValue = "bytes=86-75, 3-9RaNDoJunK";
        //WHEN
        String actual = RangeHeaderParser.andRemoveWordsFrom(rangeValue);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void splitEndsOfReturnsALongArrayFromASparseStartingString() {
        //GIVEN
        String[] expected = { "0", "8675309"};
        String rangeValue = "-8675309";
        //WHEN
        String[] actual = RangeHeaderParser.splitEndsOf(rangeValue);
        //THEN
        assertArrayEquals(expected, actual);
    }

    @Test
    public void splitEndsOfReturnsALongArrayFromASparseEndingString() {
        //GIVEN
        String[] expected = { "7", ""};
        String rangeValue = "7-";
        //WHEN
        String[] actual = RangeHeaderParser.splitEndsOf(rangeValue);
        //THEN
        assertArrayEquals(expected, actual);
    }

}