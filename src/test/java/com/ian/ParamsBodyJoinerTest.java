package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParamsBodyJoinerTest {
    @Test
    public void joinBodyWithParamsJoinsRequestBodyWithURLParams() {
        //GIVEN
        String[] expected = {"I'm first","I'm second to last","I'm at the end"};
        String body = "I'm first";
        String[] params = {"I'm second to last", "I'm at the end"};
        //WHEN
        String[] actual = ParamsBodyJoiner.joinBodyWithParams(body, params);
        //THEN
        assertArrayEquals(expected, actual);
    }
}