package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class HTCPCPListerTest {
    @Test
    public void checkReturnsTrueIfTheStringIsCoffee() {
        String request = "/coffee";
        boolean check = HTCPCPLister.check(request);
        assertTrue(check);
    }

    @Test
    public void checkReturnsTrueIfTheStringIsTea() {
        String request = "/tea";
        boolean check = HTCPCPLister.check(request);
        assertTrue(check);
    }

    @Test
    public void checkReturnsFalseIfTheStringIsSomethingOtherThanCoffeeOrTea() {
        String request = "/neither-coffee-nor-tea";
        boolean check = HTCPCPLister.check(request);
        assertFalse(check);
    }

    @Test
    public void checkCoffeeReturnsTrueIfTheStringIsCoffee() {
        String request = "/coffee";
        boolean check = HTCPCPLister.checkCoffee(request);
        assertTrue(check);
    }

    @Test
    public void checkCoffeeReturnsFalseIfTheStringIsNotCoffee() {
        String request = "/not-coffee";
        boolean check = HTCPCPLister.checkCoffee(request);
        assertFalse(check);
    }

    @Test
    public void checkTeaReturnsTrueIfTheStringIsTea() {
        String request = "/tea";
        boolean check = HTCPCPLister.checkTea(request);
        assertTrue(check);
    }

    @Test
    public void checkTeaReturnsFalseIfTheStringIsNotTea() {
        String request = "/not-tea";
        boolean check = HTCPCPLister.checkTea(request);
        assertFalse(check);
    }
}