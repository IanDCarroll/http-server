package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class AuthValidatorTest {
    @Test
    public void unauthorizedReturnsTrueForLogs() {
        //GIVEN
        String url = "/logs";
        String unParsedHeaders = "";
        //WHEN
        boolean actual = AuthValidator.unauthorized(url, unParsedHeaders);
        //THEN
        assertTrue(actual);
    }

    @Test
    public void authorizationRequiredReturnsTrueForLogs() {
        String url = "/logs";
        boolean actual = AuthValidator.authorizationRequired(url);
        assertTrue(actual);
    }

}