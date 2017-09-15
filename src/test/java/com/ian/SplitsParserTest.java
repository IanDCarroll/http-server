package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class SplitsParserTest {
    @Test
    public void bisectStringBisectsHeadFromBody() {
        String[] expected = { "Head", "Body" };
        String target = "\r\n\r\n";
        String subject = "Head"+target+"Body";
        String[] actual = SplitsParser.bisectString(subject, target);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void bisectStringReturnsEmptySecondValueWhenNothingToBisect() {
        String[] expected = { "Head", "" };
        String target = "\r\n\r\n";
        String subject = "Head"+target;
        String[] actual = SplitsParser.bisectString(subject, target);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void bisectStringReturnsEmptySecondValueWhenNoTarget() {
        String[] expected = { "Head", "" };
        String target = "\r\n\r\n";
        String subject = "Head";
        String[] actual = SplitsParser.bisectString(subject, target);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void bisectStringBisectsStartLineFromHeaders() {
        String[] expected = { "StartLine", "Header1\nHeader2" };
        String target = "\n";
        String subject = "StartLine"+target+"Header1"+target+"Header2";
        String[] actual = SplitsParser.bisectString(subject, target);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void bisectStringBisectsBisectsURLFromParameters() {
        String[] expected = { "URL", "Param%201&Param%202" };
        String target = "\\?";
        String subject = "URL?Param%201&Param%202";
        String[] actual = SplitsParser.bisectString(subject, target);
        assertArrayEquals(expected, actual);
    }
}