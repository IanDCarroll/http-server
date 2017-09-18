package com.ian;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogKeeperTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";
    public static final String log = "/test-log";

    @After
    public void tearDown() {
        FileHelper.ensureDeletion(directory, log);
    }

    @Test
    public void LogRequestReturnsTheOriginalRequest() {
        //GIVEN
        String originalRequest = "GET / HTTP/1.1\r\n\r\n";
        //WHEN
        String actual = LogKeeper.logRequest(directory, log, originalRequest);
        //THEN
        assertEquals(originalRequest, actual);
    }

    @Test
    public void LogRequestCreatesLogFileAndAddsRequestToIt() {
        //GIVEN
        String request = "GET / HTTP/1.1\r\n\r\n";
        String expected = "\n" + request;
        //WHEN
        LogKeeper.logRequest(directory, log, request);
        //THEN
        String actual = new String(FileHelper.getFileBytes(directory, log));
        assertEquals(expected, actual);
    }

    @Test
    public void LogRequestAppendsLogToExistingLogFile() {
        //GIVEN
        String originalLog = "\nGET / HTTP/1.1\r\n\r\n";
        String newRequest = "DELETE /file1 HTTP/1.1\r\n\r\n";
        String expected = originalLog + "\n" + newRequest;
        FileHelper.setFileBytes(directory, log, originalLog.getBytes());
        //WHEN
        LogKeeper.logRequest(directory, log, newRequest);
        //THEN
        String actual = new String(FileHelper.getFileBytes(directory, log));
        assertEquals(expected, actual);
    }
}