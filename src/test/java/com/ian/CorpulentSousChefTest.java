package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class CorpulentSousChefTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void craftResponseBodyReturnsTheBodyFoundInAFile() {
        String request = "/file1";
        String[] params = {};
        byte[] expected = "file1 contents".getBytes();
        byte[] actual = CorpulentSousChef.craftResponseBody(directory, request, params);
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craftResponseBodyReturnsTheDirectoryContents() {
        String request = "/";
        String[] params = {};
        byte[] expected =
                ("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<a href=\"/file1\">file1</a>\n" +
                "<a href=\"/file2\">file2</a>\n" +
                "<a href=\"/form\">form</a>\n" +
                "<a href=\"/image.gif\">image.gif</a>\n" +
                "<a href=\"/image.jpeg\">image.jpeg</a>\n" +
                "<a href=\"/image.png\">image.png</a>\n" +
                "<a href=\"/logs\">logs</a>\n" +
                "<a href=\"/partial_content.txt\">partial_content.txt</a>\n" +
                "<a href=\"/patch-content.txt\">patch-content.txt</a>\n" +
                "<a href=\"/text-file.txt\">text-file.txt</a>" +
                "\n</body>" +
                "\n</html>").getBytes();
        byte[] actual = CorpulentSousChef.craftResponseBody(directory, request, params);
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void theSousChefPersonallyAppologizesForNonExistentFiles() {
        String request = "/cheddar";
        String[] params = {};
        String personal = "Corpulent Sous Chef";
        String apology = "apologize";
        String actual = new String(CorpulentSousChef.craftResponseBody(directory, request, params));
        assert(actual.contains(personal));
        assert(actual.contains(apology));
    }

    @Test
    public void theSousChefReturnsAnyParamsPassedToIt() {
        String request = "/cheddar";
        String[] params = {"type=welsh"};
        String returnedParam = "type = welsh";
        String actual = new String(CorpulentSousChef.craftResponseBody(directory, request, params));
        assert(actual.contains(returnedParam));
    }

    @Test
    public void craftResponseBodyCraftsHTCPCPCCoffeeRequests() {
        String request = "/coffee";
        String[] params = {};
        byte[] expected = "I'm a teapot.".getBytes();
        byte[] actual = CorpulentSousChef.craftResponseBody(directory, request, params);
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craftResponseBodyCraftsHTCPCPTeaRequests() {
        String request = "/tea";
        String[] params = {};
        byte[] expected = "I'm a teapot.".getBytes();
        byte[] actual = CorpulentSousChef.craftResponseBody(directory, request, params);
        assertEquals(new String(expected), new String(actual));
    }
}