package com.ian;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestValidatorTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void validateSendsRequesttoMethodizerIfThereIsNoProblem() {
        String expected = "HTTP/1.1 200 OK";
        String request = "GET / HTTP/1.1";
        byte[] actual = RequestValidator.validate(directory, request);
        assertTrue(new String(actual).contains(expected));
    }

    @Test
    public void validateRespondsIfRequestIsNull() {
        String expected = "HTTP/1.1 400 Bad Request";
        String request = "";
        byte[] actual = RequestValidator.validate(directory, request);
        assertTrue(new String(actual).contains(expected));
    }

    @Test
    public void validateRequiresAuthForLogs() {
        String expected = "HTTP/1.1 401 Unauthorized";
        String request = "GET /logs HTTP:1.1";
        byte[] actual = RequestValidator.validate(directory, request);
        assertTrue(new String(actual).contains(expected));
    }

    @Test
    public void validateReturns416AuthForBadRangeRequests() {
        String expected = "HTTP/1.1 416 Range Not Satisfiable";
        String request = "GET /file1 HTTP:1.1" +
                        "\nRange: bytes=0-15";
        byte[] actual = RequestValidator.validate(directory, request);
        assertTrue(new String(actual).contains(expected));
    }
}