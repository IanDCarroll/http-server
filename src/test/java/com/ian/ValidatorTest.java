package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void validateSendsRequesttoMethodizerIfThereIsNoProblem() {
        String expected = "HTTP/1.1 200 OK";
        String request = "GET / HTTP/1.1";
        byte[] actual = Validator.validate(directory, request);
        assertTrue(new String(actual).contains(expected));
    }

    @Test
    public void validateRespondsIfRequestIsNull() {
        String expected = "HTTP/1.1 400 Bad Request";
        String request = "";
        byte[] actual = Validator.validate(directory, request);
        assertTrue(new String(actual).contains(expected));
    }

    @Test
    public void validateRequiresAuthForLogs() {
        String expected = "HTTP/1.1 401 Unauthorized";
        String request = "GET /logs HTTP:1.1";
        byte[] actual = Validator.validate(directory, request);
        //System.out.println(new String(actual));
        assertTrue(new String(actual).contains(expected));
    }

    @Test
    public void unauthorizedReturnsTrueForLogs() {
        String url = "/logs";
        String unParsedHeaders = "";
        boolean actual = Validator.unauthorized(url, unParsedHeaders);
        assertTrue(actual);
    }

    @Test
    public void authorizationRequiredReturnsTrueForLogs() {
        String url = "/logs";
        boolean actual = Validator.authorizationRequired(url);
        assertTrue(actual);
    }

}