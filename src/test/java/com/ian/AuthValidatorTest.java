package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class AuthValidatorTest {
    @Test
    public void unauthorizedReturnsTrueForLogs() {
        String url = "/logs";
        String unParsedHeaders = "";
        boolean actual = AuthValidator.unauthorized(url, unParsedHeaders);
        assertTrue(actual);
    }

    @Test
    public void authorizationRequiredReturnsTrueForLogs() {
        String url = "/logs";
        boolean actual = AuthValidator.authorizationRequired(url);
        assertTrue(actual);
    }

}