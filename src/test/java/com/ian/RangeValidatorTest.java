package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class RangeValidatorTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";
    public static final String url = "/file1";

    @Test
    public void notInRangeReturnsFalseIfItsNotARangeRequest() {
        String headers = "NoRangeHeader: true";
        assertFalse(RangeValidator.notInRange(directory, url, headers));
    }

    @Test
    public void notInRangeReturnsTrueIfTheRequestIsNotInBytes() {
        String headers = "Range: kiloWatts=2-7";
        assertTrue(RangeValidator.notInRange(directory, url, headers));
    }

    @Test
    public void notInRangeReturnsTrueIfTheRangeStartIsGreaterThanEnd() {
        String headers = "Range: bytes=10-7";
        assertTrue(RangeValidator.notInRange(directory, url, headers));
    }

    @Test
    public void notInRangeReturnsFalseIfTheRangeStartIsEqualToEnd() {
        String headers = "Range: bytes=10-10";
        assertFalse(RangeValidator.notInRange(directory, url, headers));
    }

    @Test
    public void notInRangeReturnsTrueIfTheRangeEndIsGreaterThanSize() {
        String headers = "Range: bytes=0-15";
        assertTrue(RangeValidator.notInRange(directory, url, headers));
    }

    @Test
    public void itIsNotInBytesReturnsFalseIfTheHeaderRequestIsInBytes() {
        String header = "Range: bytes=0-12";
        assertFalse(RangeValidator.itIsNotInBytes(header));
    }

    @Test
    public void itIsNotInBytesReturnsTrueIfTheRequestIsNotInBytes() {
        String header = "Range: kiloWatts=30000-40000";
        assertTrue(RangeValidator.itIsNotInBytes(header));
    }

    @Test
    public void itIsARangeRequestReturnsTrueIfItIsARangeRequest() {
        String headers = "Range: whatever";
        assertTrue(RangeValidator.itIsARangeRequest(headers));
    }

    @Test
    public void itIsARangeRequestReturnsFalseIfItIsNotARangeRequest() {
        String headers = "NoRangeHeader: true";
        assertFalse(RangeValidator.itIsARangeRequest(headers));
    }
}