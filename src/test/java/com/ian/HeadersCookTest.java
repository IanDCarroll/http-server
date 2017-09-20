package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class HeadersCookTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void craftStandardHeadersReturnsContentLengthAndType() {
        //GIVEN
        String expected = "\nContent-Length: 14" +
                "\nContent-Type: text/plain";
        byte[] body = "file1 contents".getBytes();
        //WHEN
        String actual = new String(HeadersCook.craftStandardHeaders(directory, "/file1", body));
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void craftPartialHeadersReturnsContentRangeAndLength() {
        //GIVEN
        String expected = "\nContent-Range: bytes 2-13/14" +
                "\nContent-Length: 11";
        String name = "/file1";
        long start = 2;
        long end = 13;
        byte[] body = "le1 content".getBytes();
        //WHEN
        String actual = new String(HeadersCook.craftPartialHeaders(directory, name, body, start, end));
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void craftContentTypeReturnsContentTypeTextPlainAsDefault() {
        String expected = "\nContent-Type: text/plain";
        String actual = HeadersCook.craftContentType(directory, "text/plain");
        assertEquals(expected, actual);
    }

    @Test
    public void craftContentLengthReturnsContentLength() {
        //GIVEN
        String expected = "\nContent-Length: 14";
        byte [] body = "file1 contents".getBytes();
        //WHEN
        String actual = HeadersCook.craftContentLength(body);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void craftContentRangeReturnsContentRange() {
        //GIVEN
        String expected = "\nContent-Range: bytes 2-13/14";
        String name = "/file1";
        long start = 2;
        long end = 13;
        //WHEN
        String actual = HeadersCook.craftContentRange(directory, name, start, end);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void craftLocationReturnsTheURLToRedirect() {
        //GIVEN
        String expected = "\nLocation: /";
        String url = "/redirect";
        //WHEN
        String actual = HeadersCook.craftLocation(url);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void typeReturnsTextPlainTypeAsDefault() {
        String text = "text/plain";
        String actualText = HeadersCook.type(directory,"/file1");
        assertEquals(text, actualText);
    }

    @Test
    public void typeReturnsImageJpegTypeForJpegs() {
        String jpeg = "image/jpeg";
        String actualJpeg = HeadersCook.type(directory, "/image.jpeg");
        assertEquals(jpeg, actualJpeg);
    }

    @Test
    public void typeReturnsImageGifTypeForGifs() {
        String gif = "image/gif";
        String actualGif = HeadersCook.type(directory, "/image.gif");
        assertEquals(gif, actualGif);
    }

    @Test
    public void typeReturnsImagePngForPngs() {
        String png = "image/png";
        String actualPng = HeadersCook.type(directory, "/image.png");
        assertEquals(png, actualPng);
    }

    @Test
    public void typeReturnsTextHTMLForDirectories() {
        String html = "text/html";
        String actualHtml = HeadersCook.type(directory, "/");
        assertEquals(html, actualHtml);
    }
}