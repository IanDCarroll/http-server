package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class SplitsParserTest {
    @Test
    public void bisectStringBisectsHeadFromBody() {
        //GIVEN
        String[] expected = { "Head", "Body" };
        String target = "\r\n\r\n";
        String subject = "Head"+target+"Body";
        //WHEN
        String[] actual = SplitsParser.bisectString(subject, target);
        //THEN
        assertArrayEquals(expected, actual);
    }

    @Test
    public void bisectStringReturnsEmptySecondValueWhenNothingToBisect() {
        //GIVEN
        String[] expected = { "Head", "" };
        String target = "\r\n\r\n";
        String subject = "Head"+target;
        //WHEN
        String[] actual = SplitsParser.bisectString(subject, target);
        //THEN
        assertArrayEquals(expected, actual);
    }

    @Test
    public void bisectStringReturnsEmptySecondValueWhenNoTarget() {
        //GIVEN
        String[] expected = { "Head", "" };
        String target = "\r\n\r\n";
        String subject = "Head";
        //WHEN
        String[] actual = SplitsParser.bisectString(subject, target);
        //THEN
        assertArrayEquals(expected, actual);
    }

    @Test
    public void bisectStringBisectsStartLineFromHeaders() {
        //GIVEN
        String[] expected = { "StartLine", "Header1\nHeader2" };
        String target = "\n";
        String subject = "StartLine"+target+"Header1"+target+"Header2";
        //WHEN
        String[] actual = SplitsParser.bisectString(subject, target);
        //THEN
        assertArrayEquals(expected, actual);
    }

    @Test
    public void bisectStringBisectsBisectsURLFromParameters() {
        //GIVEN
        String[] expected = { "URL", "Param%201&Param%202" };
        String target = "\\?";
        String subject = "URL?Param%201&Param%202";
        //WHEN
        String[] actual = SplitsParser.bisectString(subject, target);
        //THEN
        assertArrayEquals(expected, actual);
    }
}