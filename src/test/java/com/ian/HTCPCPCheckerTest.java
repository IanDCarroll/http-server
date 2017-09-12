package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class HTCPCPCheckerTest {
    @Test
    public void checkReturnsTrueIfTheStringIsCoffee() {
        String request = "/coffee";
        assertTrue(HTCPCPChecker.check(request));
    }

    @Test
    public void checkReturnsTrueIfTheStringIsTea() {
        String request = "/tea";
        assertTrue(HTCPCPChecker.check(request));
    }

    @Test
    public void checkReturnsFalseIfTheStringIsSomethingOtherThanCoffeeOrTea() {
        String request = "/neither-coffee-nor-tea";
        assertFalse(HTCPCPChecker.check(request));
    }

    @Test
    public void checkCoffeeReturnsTrueIfTheStringIsCoffee() {
        String request = "/coffee";
        assertTrue(HTCPCPChecker.checkCoffee(request));
    }

    @Test
    public void checkCoffeeReturnsFalseIfTheStringIsNotCoffee() {
        String request = "/not-coffee";
        assertFalse(HTCPCPChecker.checkCoffee(request));
    }

    @Test
    public void checkTeaReturnsTrueIfTheStringIsTea() {
        String request = "/tea";
        assertTrue(HTCPCPChecker.checkTea(request));
    }

    @Test
    public void checkTeaReturnsFalseIfTheStringIsNotTea() {
        String request = "/not-tea";
        assertFalse(HTCPCPChecker.checkTea(request));
    }
}