package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParamsChefTest {
    @Test
    public void plateParamsReturnsEmptyArrayWhenNoParamsPassed() {
        byte[] expected = {};
        String[] given = {};
        byte[] actual = ParamsChef.plateParams(given);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void plateParamsTransformsAStringArrayIntoAByteArray() {
        byte[] expected = ("name = Sir Lancelot of Camelot\n" +
                           "quest = To seek the Holy Grail\n" +
                           "favorite-color = blue").getBytes();
        String[] given = {"name = Sir Lancelot of Camelot",
                          "quest = To seek the Holy Grail",
                          "favorite-color = blue"};
        byte[] actual = ParamsChef.plateParams(given);
        assertArrayEquals(expected, actual);
    }
}