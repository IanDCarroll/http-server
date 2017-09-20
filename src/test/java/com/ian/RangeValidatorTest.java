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
        boolean inRange = RangeValidator.notInRange(directory, url, headers);
        assertFalse(inRange);
    }

    @Test
    public void notInRangeReturnsTrueIfTheRequestIsNotInBytes() {
        String headers = "Range: kiloWatts=2-7";
        boolean inRange = RangeValidator.notInRange(directory, url, headers);
        assertTrue(inRange);
    }

    @Test
    public void notInRangeReturnsTrueIfTheRangeStartIsGreaterThanEnd() {
        String headers = "Range: bytes=10-7";
        assertTrue(RangeValidator.notInRange(directory, url, headers));
    }

    @Test
    public void notInRangeReturnsFalseIfTheRangeStartIsEqualToEnd() {
        String headers = "Range: bytes=10-10";
        boolean inRange = RangeValidator.notInRange(directory, url, headers);
        assertFalse(inRange);
    }

    @Test
    public void notInRangeReturnsTrueIfTheRangeEndIsGreaterThanSize() {
        String headers = "Range: bytes=0-15";
        boolean inRange = RangeValidator.notInRange(directory, url, headers);
        assertTrue(inRange);
    }

    @Test
    public void itIsNotInBytesReturnsFalseIfTheHeaderRequestIsInBytes() {
        String header = "Range: bytes=0-12";
        boolean notInBytes = RangeValidator.itIsNotInBytes(header);
        assertFalse(notInBytes);
    }

    @Test
    public void itIsNotInBytesReturnsTrueIfTheRequestIsNotInBytes() {
        String header = "Range: kiloWatts=30000-40000";
        boolean notInBytes = RangeValidator.itIsNotInBytes(header);
        assertTrue(notInBytes);
    }

    @Test
    public void itIsARangeRequestReturnsTrueIfItIsARangeRequest() {
        String headers = "Range: whatever";
        boolean rangeRequest = RangeValidator.itIsARangeRequest(headers);
        assertTrue(rangeRequest);
    }

    @Test
    public void itIsARangeRequestReturnsFalseIfItIsNotARangeRequest() {
        String headers = "NoRangeHeader: true";
        boolean rangeRequest = RangeValidator.itIsARangeRequest(headers);
        assertFalse(rangeRequest);
    }
}