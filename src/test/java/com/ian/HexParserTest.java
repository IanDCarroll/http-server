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
        String expected = "a";
        String params = "%61";
        String actual = HexParser.unHex(params);
        assertEquals(expected, actual);
    }

    @Test
    public void unHexReturnsTheUnHexedStringFromMixedPercentEncoding() {
        String expected = "NaCl";
        String params = "%4E%61Cl";
        String actual = HexParser.unHex(params);
        assertEquals(expected, actual);
    }

    @Test
    public void unHexCharReturnsTheChar_a_AsStringFromHex() {
        String expected = "a";
        String hex = "61";
        assertEquals(expected, HexParser.unHexChar(hex));
    }

    @Test
    public void unHexCharReturnsTheChar_N_AsStringFromHex() {
        String expected = "N";
        String hex = "4e";
        assertEquals(expected, HexParser.unHexChar(hex));
    }

    @Test
    public void unHexCharIsIndifferentToCapitalization() {
        String expected = "N";
        String hex = "4E";
        assertEquals(expected, HexParser.unHexChar(hex));
    }
}