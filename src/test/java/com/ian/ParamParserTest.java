package com.ian;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ParamParserTest {
    @Test
    public void parseParamsReturnsAStringArrayOfParams() {
        String[] expected = { "name=Slartibartfast",
                              "occupation=Fjord Designer" };

        String params = "name=Slartibartfast&occupation=Fjord%20Designer";
        String[] actual = ParamParser.parseParams(params);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void unHexReturnsTheSameStringIfNoPercentEncoding() {
        String expected = "name=Slartibartfast&occupation=Fjordsmith";
        String actual = ParamParser.unHex(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void unHexReturnsTheUnHexedCharFromPercentEncoding() {
        String expected = "a";
        String params = "%61";
        String actual = ParamParser.unHex(params);
        assertEquals(expected, actual);
    }

    @Test
    public void unHexReturnsTheUnHexedStringFromMixedPercentEncoding() {
        String expected = "NaCl";
        String params = "%4E%61Cl";
        String actual = ParamParser.unHex(params);
        assertEquals(expected, actual);
    }

    @Test
    public void unHexCharReturnsTheChar_a_AsStringFromHex() {
        String expected = "a";
        String hex = "61";
        assertEquals(expected, ParamParser.unHexChar(hex));
    }

    @Test
    public void unHexCharReturnsTheChar_N_AsStringFromHex() {
        String expected = "N";
        String hex = "4e";
        assertEquals(expected, ParamParser.unHexChar(hex));
    }

    @Test
    public void unHexCharIsIndifferentToCapitalization() {
        String expected = "N";
        String hex = "4E";
        assertEquals(expected, ParamParser.unHexChar(hex));
    }
}