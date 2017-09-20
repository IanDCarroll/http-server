package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class AssignmentParserTest {
    @Test
    public  void expandAssignmentOperatorsReturnsParamsWithSpacesAroundEqualsSign() {
        //GIVEN
        String[] expected = { "name = Sir Lancelot of Camelot",
                "quest = To seek the Holy Grail",
                "favorite-color = blue" };
        String[] unExpanded = { "name=Sir Lancelot of Camelot",
                "quest=To seek the Holy Grail",
                "favorite-color=blue" };
        //WHEN
        String[] actual = AssignmentParser.expandAssignmentOperators(unExpanded);
        //THEN
        assertArrayEquals(expected, actual);
    }

    @Test
    public  void expandAssignmentOperatorsIgnoresFurtherEqualsSigns() {
        //GIVEN
        String[] expected = { "name = Sir Lancelot => Camelot"};
        String[] given = { "name=Sir Lancelot => Camelot"};
        //WHEN
        String[] actual = AssignmentParser.expandAssignmentOperators(given);
        //THEN
        assertArrayEquals(expected, actual);
    }

}