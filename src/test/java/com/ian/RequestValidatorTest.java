package com.ian;

import org.junit.Test;
import static org.junit.Assert.*;

public class RequestValidatorTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void validateSendsRequestToMethodizerIfThereIsNoProblem() {
        //GIVEN
        String expected = "HTTP/1.1 200 OK";
        String request = "GET / HTTP/1.1";
        //WHEN
        byte[] actual = RequestValidator.validate(directory, request);
        //THEN
        assertTrue(new String(actual).contains(expected));
    }

    @Test
    public void validateRespondsIfRequestIsNull() {
        //GIVEN
        String expected = "HTTP/1.1 400 Bad Request";
        String request = "";
        //WHEN
        byte[] actual = RequestValidator.validate(directory, request);
        //THEN
        assertTrue(new String(actual).contains(expected));
    }

    @Test
    public void validateRequiresAuthForLogs() {
        //GIVEN
        String expected = "HTTP/1.1 401 Unauthorized";
        String request = "GET /logs HTTP:1.1";
        //WHEN
        byte[] actual = RequestValidator.validate(directory, request);
        //THEN
        assertTrue(new String(actual).contains(expected));
    }

    @Test
    public void validateReturns416AuthForBadRangeRequests() {
        //GIVEN
        String expected = "HTTP/1.1 416 Range Not Satisfiable";
        String request = "GET /file1 HTTP:1.1" +
                        "\nRange: bytes=0-15";
        //WHEN
        byte[] actual = RequestValidator.validate(directory, request);
        //THEN
        assertTrue(new String(actual).contains(expected));
    }
}