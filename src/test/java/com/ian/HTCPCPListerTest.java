package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class HTCPCPListerTest {
    @Test
    public void checkReturnsTrueIfTheStringIsCoffee() {
        String request = "/coffee";
        assertTrue(HTCPCPLister.check(request));
    }

    @Test
    public void checkReturnsTrueIfTheStringIsTea() {
        String request = "/tea";
        assertTrue(HTCPCPLister.check(request));
    }

    @Test
    public void checkReturnsFalseIfTheStringIsSomethingOtherThanCoffeeOrTea() {
        String request = "/neither-coffee-nor-tea";
        assertFalse(HTCPCPLister.check(request));
    }

    @Test
    public void checkCoffeeReturnsTrueIfTheStringIsCoffee() {
        String request = "/coffee";
        assertTrue(HTCPCPLister.checkCoffee(request));
    }

    @Test
    public void checkCoffeeReturnsFalseIfTheStringIsNotCoffee() {
        String request = "/not-coffee";
        assertFalse(HTCPCPLister.checkCoffee(request));
    }

    @Test
    public void checkTeaReturnsTrueIfTheStringIsTea() {
        String request = "/tea";
        assertTrue(HTCPCPLister.checkTea(request));
    }

    @Test
    public void checkTeaReturnsFalseIfTheStringIsNotTea() {
        String request = "/not-tea";
        assertFalse(HTCPCPLister.checkTea(request));
    }
}