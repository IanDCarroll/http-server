package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class ByteArrayCookTest {
    @Test
    public void marinateBytesConcatenatesByteArrays() {
        String expected = "Shall I compare thee to a summer’s day?\nThou art more lovely and more temperate.\nRough " +
                "winds do shake the darling buds of May,\nAnd summer’s lease hath all too short a date.\n\nSometime " +
                "too hot the eye of heaven shines,\nAnd often is his gold complexion dimmed\nAnd every fair from " +
                "fair sometime declines,\nBy chance, or nature’s changing course, untrimmed;\n\nBut thy eternal " +
                "summer shall not fade,\nNor lose possession of that fair thou ow’st,\nNor shall death brag thou " +
                "wand’rest in his shade,\nWhen in eternal lines to Time thou grow’st.\n\n\tSo long as men can " +
                "breathe, or eyes can see,\n\tSo long lives this, and this gives life to the.";

        byte[] line1 = "Shall I compare thee to a summer’s day?\n".getBytes();
        byte[] line2 = "Thou art more lovely and more temperate.\n".getBytes();
        byte[] line3 = "Rough winds do shake the darling buds of May,\n".getBytes();
        byte[] line4 = "And summer’s lease hath all too short a date.\n\n".getBytes();
        byte[] line5 = "Sometime too hot the eye of heaven shines,\n".getBytes();
        byte[] line6 = "And often is his gold complexion dimmed\n".getBytes();
        byte[] line7 = "And every fair from fair sometime declines,\n".getBytes();
        byte[] line8 = "By chance, or nature’s changing course, untrimmed;\n\n".getBytes();
        byte[] line9 = "But thy eternal summer shall not fade,\n".getBytes();
        byte[] lineA = "Nor lose possession of that fair thou ow’st,\n".getBytes();
        byte[] lineB = "Nor shall death brag thou wand’rest in his shade,\n".getBytes();
        byte[] lineC = "When in eternal lines to Time thou grow’st.\n\n".getBytes();
        byte[] lineD = "\tSo long as men can breathe, or eyes can see,\n".getBytes();
        byte[] lineE = "\tSo long lives this, and this gives life to the.".getBytes();
        byte[][] allInputs = { line1, line2, line3, line4, line5, line6, line7,
                               line8, line9, lineA, lineB, lineC, lineD, lineE };
        String actual = new String(ByteArrayCook.concatenateByteArrays(allInputs));
        assertEquals(expected, actual);
    }

    @Test
    public void getRightSizedPotReturnsTheCombinedSizeOfAllByteArrays() {
        byte[] line1 = "When most I wink, then do mine eyes best see,".getBytes();
        byte[] line2 = "For all the day they view things unrespected;".getBytes();
        byte[] line3 = "But when I sleep, in dreams they look on thee,".getBytes();
        byte[] line4 = "And darkly bright are bright in dark directed.".getBytes();
        byte[][] allInputs = { line1, line2, line3, line4 };
        int expected = line1.length + line2.length + line3.length + line4.length;
        int actual = ByteArrayCook.getRightSizedPot(allInputs);
        assertEquals(expected, actual);
    }
}