package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParamsParserTest {
    @Test
    public void parseParamsReturnsAStringArrayWhenNotPassedParams() {
        //GIVEN
        String[] expected = {};
        String params = "";
        //WHEN
        String[] actual = ParamsParser.parseParams(params);
        //THEN
        assertArrayEquals(expected, actual);
    }

    @Test
    public void parseParamsReturnsAStringArrayWithThePassedParam() {
        //GIVEN
        String[]expected = {"name=Sir Lancelot of Camelot"};
        String params = "name=Sir%20Lancelot%20of%20Camelot";
        //WHEN
        String[] actual = ParamsParser.parseParams(params);
        //THEN
        assertArrayEquals(expected, actual);
    }

    @Test
    public void parseParamsReturnsAStringArrayWithPassedMultipleParams() {
        //GIVEN
        String[] expected = {"name=Sir Lancelot of Camelot",
                "quest=To seek the Holy Grail",
                "favorite-color=blue"};
        String params = "name=Sir%20Lancelot%20of%20Camelot&" +
                "quest=To%20seek%20the%20Holy%20Grail&" +
                "favorite-color=blue";
        //WHEN
        String[] actual = ParamsParser.parseParams(params);
        //THEN
        assertArrayEquals(expected, actual);
    }

    @Test
    public void parseParamsReturnsTheCorrectStuffFromCob_Spec() {
        //GIVEN
        String[] expected = {"variable_1=Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?",
                "variable_2=stuff"};
        String url = "variable_1=Operators%20%3C%2C%20%3E" +
                "%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26" +
                "%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20" +
                "%22is%20that%20all%22%3F&variable_2=stuff";
        //WHEN
        String[] actual = ParamsParser.parseParams(url);
        //THEN
        assertArrayEquals(expected, actual);
    }
}