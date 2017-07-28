package com.ian;

import org.junit.runner.JUnitCore;

public class RunTests {
    public static void main(String[] args) {
        JUnitCore.runClasses(IntegrationTest.class);
    }
}
