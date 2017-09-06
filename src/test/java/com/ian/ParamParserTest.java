package com.ian;

import org.junit.Test;
import static org.junit.Assert.*;

public class ParamParserTest {
    @Test
    public void parseURLReturnsAStringArrayWhenNotPassedParams() {
        String url = "/no-params-included";
        String[][] expected = {{url}, {}};
        String[][] actual = ParamParser.parseUrl(url);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void parseURLReturnsAStringArrayWithAPassedParam() {
        String url = "/one-param-included?name=Sir%20Lancelot%20of%20Camelot";
        String[][] expected = {{"/one-param-included"},
                               {"name=Sir Lancelot of Camelot"}};
        String[][] actual = ParamParser.parseUrl(url);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void parseURLReturnsAStringArrayWithPassedMultipleParams() {
        String url = "/one-param-included?" +
                     "name=Sir%20Lancelot%20of%20Camelot&" +
                     "quest=To%20seek%20the%20Holy%20Grail&" +
                     "favorite-color=blue";
        String[][] expected = {{"/one-param-included"},
                               {"name=Sir Lancelot of Camelot",
                                "quest=To seek the Holy Grail",
                                "favorite-color=blue"}};
        String[][] actual = ParamParser.parseUrl(url);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void parseURLReturnsTheCorrectStuffFromCob_Spec() {
        String url = "/parameters?variable_1=Operators%20%3C%2C%20%3E" +
                "%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26" +
                "%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20" +
                "%22is%20that%20all%22%3F&variable_2=stuff";
        String[][] expected = {{"/parameters"},
                               {"variable_1=Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?",
                                "variable_2=stuff"}};
        String[][] actual = ParamParser.parseUrl(url);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void parseToUnsmushedParamsReturnsEmptyStringArrayFromEmptyString() {
        String[] expected = {};
        String params = "";
        String[] actual = ParamParser.parseParams(params);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void parseParamsReturnsAStringArrayOfStillSmushedParams() {
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

    @Test
    public  void unSmushParamsReturnsParamsWithSpacesAroundEqualsSign() {
        String[] expected = { "name = Sir Lancelot of Camelot",
                              "quest = To seek the Holy Grail",
                              "favorite-color = blue" };
        String[] given = { "name=Sir Lancelot of Camelot",
                           "quest=To seek the Holy Grail",
                           "favorite-color=blue" };
        String[] actual = ParamParser.unSmushParams(given);
        assertArrayEquals(expected, actual);
    }

    @Test
    public  void unSmushParamsIgnoresFurtherEqualsSigns() {
        String[] expected = { "name = Sir Lancelot => Camelot"};
        String[] given = { "name=Sir Lancelot => Camelot"};
        String[] actual = ParamParser.unSmushParams(given);
        assertArrayEquals(expected, actual);
    }
}