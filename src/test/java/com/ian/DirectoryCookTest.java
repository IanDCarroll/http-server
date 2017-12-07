package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class DirectoryCookTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void craftResponseBodyReturnsTheDirectoryContents() {
        byte[] expected =("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<a href=\"/form\">form</a>\n" +
                "<a href=\"/text-file.txt\">text-file.txt</a>\n" +
                "<a href=\"/file2\">file2</a>\n" +
                "<a href=\"/patch-content.txt\">patch-content.txt</a>\n" +
                "<a href=\"/image.gif\">image.gif</a>\n" +
                "<a href=\"/logs\">logs</a>\n" +
                "<a href=\"/image.jpeg\">image.jpeg</a>\n" +
                "<a href=\"/file1\">file1</a>\n" +
                "<a href=\"/partial_content.txt\">partial_content.txt</a>\n" +
                "<a href=\"/image.png\">image.png</a>\n" +
                "</body>\n" +
                "</html>").getBytes();
        String request = "/";
        String[] params = {};
        byte[] actual = DirectoryCook.craftResponseLinks(directory, request, params);
        assertEquals(new String(expected), new String(actual));
    }

    @Test
    public void craftResponseBodyReturnsTheDirectoryContentsWithParams() {
        //GIVEN
        byte[] expected =("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<a href=\"/form\">form</a>\n" +
                "<a href=\"/text-file.txt\">text-file.txt</a>\n" +
                "<a href=\"/file2\">file2</a>\n" +
                "<a href=\"/patch-content.txt\">patch-content.txt</a>\n" +
                "<a href=\"/image.gif\">image.gif</a>\n" +
                "<a href=\"/logs\">logs</a>\n" +
                "<a href=\"/image.jpeg\">image.jpeg</a>\n" +
                "<a href=\"/file1\">file1</a>\n" +
                "<a href=\"/partial_content.txt\">partial_content.txt</a>\n" +
                "<a href=\"/image.png\">image.png</a>\n" +
                "<p>data = fatcat</p>\n" +
                "<p>data = heathcliff</p>\n" +
                "</body>\n" +
                "</html>").getBytes();
        String request = "/";
        String[] params = { "data=fatcat", "data=heathcliff" };
        //WHEN
        byte[] actual = DirectoryCook.craftResponseLinks(directory, request, params);
        //THEN
        assertEquals(new String(expected), new String(actual));
    }
}