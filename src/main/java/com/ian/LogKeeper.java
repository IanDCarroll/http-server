package com.ian;

public class LogKeeper {
    public static synchronized String logRequest(String directory, String logFile, String requestToBeLogged) {
        byte[] requestWithNewLine = ("\n" + requestToBeLogged).getBytes();
        FileStocker.pushBytes(directory, logFile, requestWithNewLine);
        return requestToBeLogged;
    }
}
