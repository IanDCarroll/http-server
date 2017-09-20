package com.ian;

import org.junit.Test;
import static org.junit.Assert.*;

public class CorpulentSousChefTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void craftResponseBodyReturnsTheBodyFoundInAFile() {
        //GIVEN
        byte[] expected = "file1 contents".getBytes();
        String request = "/file1";
        String[] params = {};
        //WHEN
        byte[] actual = CorpulentSousChef.craftResponseBody(directory, request, params);
        //THEN
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craftResponseBodyReturnsTheDirectoryContents() {
        //GIVEN
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
        String request = "/";
        String[] params = {};
        //WHEN
        byte[] actual = CorpulentSousChef.craftResponseBody(directory, request, params);
        //THEN
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void theSousChefPersonallyAppologizesForNonExistentFiles() {
        //GIVEN
        String personal = "Corpulent Sous Chef";
        String apology = "apologize";
        String request = "/cheddar";
        String[] params = {};
        //WHEN
        String actual = new String(CorpulentSousChef.craftResponseBody(directory, request, params));
        //THEN
        assertTrue(actual.contains(personal));
        assertTrue(actual.contains(apology));
    }

    @Test
    public void theSousChefReturnsAnyParamsPassedToIt() {
        //GIVEN
        String returnedParam = "type = welsh";
        String request = "/cheddar";
        String[] params = {"type=welsh"};
        //WHEN
        String actual = new String(CorpulentSousChef.craftResponseBody(directory, request, params));
        //THEN
        assertTrue(actual.contains(returnedParam));
    }

    @Test
    public void craftResponseBodyCraftsHTCPCPCCoffeeRequests() {
        //GIVEN
        byte[] expected = "I'm a teapot.".getBytes();
        String request = "/coffee";
        String[] params = {};
        //WHEN
        byte[] actual = CorpulentSousChef.craftResponseBody(directory, request, params);
        //THEN
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craftResponseBodyCraftsHTCPCPTeaRequests() {
        //GIVEN
        byte[] expected = "I'm a teapot.".getBytes();
        String request = "/tea";
        String[] params = {};
        //WHEN
        byte[] actual = CorpulentSousChef.craftResponseBody(directory, request, params);
        //THEN
        assertEquals(new String(expected), new String(actual));
    }
}