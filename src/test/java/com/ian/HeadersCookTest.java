package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class HeadersCookTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void craftStandardHeadersReturnsContentLengthAndType() {
        byte[] body = "file1 contents".getBytes();
        String expected = "\nContent-Length: 14" +
                           "\nContent-Type: text/plain";
        String actual = new String(HeadersCook.craftStandardHeaders(directory, "/file1", body));
        assertEquals(expected, actual);
    }

    @Test
    public void craftContentTypeReturnsContentTypeTextPlainAsDefault() {
        String expected = "\nContent-Type: text/plain";
        assertEquals(expected, HeadersCook.craftContentType(directory, "text/plain"));
    }

    @Test
    public void craftContentLengthReturnsContentLength() {
        byte [] body = "file1 contents".getBytes();
        String expected = "\nContent-Length: 14";
        assertEquals(expected, HeadersCook.craftContentLength(body));
    }

    @Test
    public void craftLocationReturnsTheURLToRedirect() {
        String url = "/redirect";
        String expected = "\nLocation: /";
        String actual = HeadersCook.craftLocation(url);
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