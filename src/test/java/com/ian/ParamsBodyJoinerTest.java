package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParamsBodyJoinerTest {
    @Test
    public void joinBodyWithParamsJoinsRequestBodyWithURLParams() {
        String body = "I'm first";
        String[] params = {"I'm second to last", "I'm at the end"};
        String[] expected = {"I'm first","I'm second to last","I'm at the end"};
        String[] actual = ParamsBodyJoiner.joinBodyWithParams(body, params);
        assertArrayEquals(expected, actual);
    }
}