package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParamsCookTest {
    @Test
    public void craftParamsReturnsEmptyArrayWhenNoParamsPassed() {
        //GIVEN
        byte[] expected = {};
        String[] emptyArray = {};
        //WHEN
        byte[] actual = ParamsCook.craftParams(emptyArray);
        //THEN
        assertArrayEquals(expected, actual);
    }

    @Test
    public void craftParamsTransformsAStringArrayIntoAByteArray() {
        //GIVEN
        byte[] expected = ("name = Sir Lancelot of Camelot" +
                           "quest = To seek the Holy Grail" +
                           "favorite-color = blue").getBytes();
        String[] stringArray = {"name = Sir Lancelot of Camelot",
                          "quest = To seek the Holy Grail",
                          "favorite-color = blue"};
        //WHEN
        byte[] actual = ParamsCook.craftParams(stringArray);
        //THEN
        assertArrayEquals(expected, actual);
    }
}