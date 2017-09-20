package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class HexParserTest {
    @Test
    public void unHexReturnsTheSameStringIfNoPercentEncoding() {
        String expected = "name=Slartibartfast&occupation=Fjordsmith";
        String actual = HexParser.unHex(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void unHexReturnsTheUnHexedCharFromPercentEncoding() {
        //GIVEN
        String expected = "a";
        String params = "%61";
        //WHEN
        String actual = HexParser.unHex(params);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void unHexReturnsTheUnHexedStringFromMixedPercentEncoding() {
        //GIVEN
        String expected = "NaCl";
        String params = "%4E%61Cl";
        //WHEN
        String actual = HexParser.unHex(params);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void unHexCharReturnsTheChar_a_AsStringFromHex() {
        //GIVEN
        String expected = "a";
        String hex = "61";
        //WHEN
        String actual = HexParser.unHexChar(hex);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void unHexCharReturnsTheChar_N_AsStringFromHex() {
        //GIVEN
        String expected = "N";
        String hex = "4e";
        //WHEN
        String actual = HexParser.unHexChar(hex);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void unHexCharIsIndifferentToCapitalization() {
        //GIVEN
        String expected = "N";
        String hex = "4E";
        //WHEN
        String actual = HexParser.unHexChar(hex);
        //THEN
        assertEquals(expected, actual);
    }
}