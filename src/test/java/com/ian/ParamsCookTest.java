package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParamsCookTest {
    @Test
    public void plateParamsReturnsEmptyArrayWhenNoParamsPassed() {
        byte[] expected = {};
        String[] given = {};
        byte[] actual = ParamsCook.craftParams(given);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void plateParamsTransformsAStringArrayIntoAByteArray() {
        byte[] expected = ("name = Sir Lancelot of Camelot" +
                           "quest = To seek the Holy Grail" +
                           "favorite-color = blue").getBytes();
        String[] given = {"name = Sir Lancelot of Camelot",
                          "quest = To seek the Holy Grail",
                          "favorite-color = blue"};
        byte[] actual = ParamsCook.craftParams(given);
        assertArrayEquals(expected, actual);
    }
}