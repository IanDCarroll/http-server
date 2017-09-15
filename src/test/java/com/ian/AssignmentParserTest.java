package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class AssignmentParserTest {
    @Test
    public  void expandAssignmentOperatorsReturnsParamsWithSpacesAroundEqualsSign() {
        String[] expected = { "name = Sir Lancelot of Camelot",
                "quest = To seek the Holy Grail",
                "favorite-color = blue" };
        String[] given = { "name=Sir Lancelot of Camelot",
                "quest=To seek the Holy Grail",
                "favorite-color=blue" };
        String[] actual = AssignmentParser.expandAssignmentOperators(given);
        assertArrayEquals(expected, actual);
    }

    @Test
    public  void expandAssignmentOperatorsIgnoresFurtherEqualsSigns() {
        String[] expected = { "name = Sir Lancelot => Camelot"};
        String[] given = { "name=Sir Lancelot => Camelot"};
        String[] actual = AssignmentParser.expandAssignmentOperators(given);
        assertArrayEquals(expected, actual);
    }

}