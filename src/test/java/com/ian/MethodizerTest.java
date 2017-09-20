package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class MethodizerTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void takeOrderReturnsFormattedResponseFromChef() {
        //GIVEN
        String expected =
                "HTTP/1.1 200 OK" +
                        "\nContent-Length: 436" +
                        "\nContent-Type: text/html" +
                        "\r\n\r\n" +
                        "<!DOCTYPE html>\n" +
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
                        "\n</html>";
        String request = "GET / HTTP/1.1";
        //WHEN
        String actual = new String(Methodizer.takeOrder(directory, request));
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void takeOrderHandlesNullRequests() {
        //GIVEN
        byte[] expected = null;
        String request = null;
        //WHEN
         byte[] actual = Methodizer.takeOrder(directory, request);
        //THEN
        assertArrayEquals(expected, actual);
    }

    @Test
    public void takeOrderDoesNotReturn0ContentLengthInPOSTResponse() {
        //GIVEN
        String notExpected = "Content-Length: 0";
        String url = "/basho";
        String request = "POST " + url + "?Poverty's%20child%20-%0A" +
                "he%20starts%20to%20grind%20rice,%0A" +
                "and%20gazes%20at%20the%20moon. HTTP/1.1";
        FileHelper.setFileBytes(directory, url, "".getBytes());
        //WHEN
        String response = new String(Methodizer.takeOrder(directory, request));
        //THEN
        FileHelper.ensureDeletion(directory, url);
        assertFalse(response.contains(notExpected));
    }

    @Test
    public void takeOrderDoesNotReturn0ContentLengthInPUTResponse() {
        //GIVEN
        String notExpected = "Content-Length: 0";
        String url = "/basho";
        String request = "PUT " + url + "?Poverty's%20child%20-%0A" +
                "he%20starts%20to%20grind%20rice,%0A" +
                "and%20gazes%20at%20the%20moon. HTTP/1.1";
        //WHEN
        String response = new String(Methodizer.takeOrder(directory, request));
        //THEN
        FileHelper.ensureDeletion(directory, url);
        assertFalse(response.contains(notExpected));
    }

    @Test
    public void takeOrderPerformsTheCobSpecTestForGetPostGetPutGetDeleteGet() {
        //GIVEN
        String status = "HTTP/1.1 200 OK";
        String content1 = "data=fatcat";
        String content2 = "data=heathcliff";

        String fileName = "/form";
        String requestBase =  " HTTP/1.1\r\n\r\n";
        String paramDelimiter = "?";
        String deleteRequest = "DELETE " + fileName + requestBase;
        String getRequest = "GET " + fileName + requestBase;
        String postRequest = "POST " + fileName + paramDelimiter + content1 + requestBase;
        String putRequest = "PUT " + fileName + paramDelimiter + content2 + requestBase;
        //WHEN
        String response1 = new String(Methodizer.takeOrder(directory, getRequest));
        String response2 = new String(Methodizer.takeOrder(directory, postRequest));
        String response3 = new String(Methodizer.takeOrder(directory, getRequest));
        String response4 = new String(Methodizer.takeOrder(directory, putRequest));
        String response5 = new String(Methodizer.takeOrder(directory, getRequest));
        String response6 = new String(Methodizer.takeOrder(directory, deleteRequest));
        String response7 = new String(Methodizer.takeOrder(directory, getRequest));
        //THEN
        assertTrue(response1.contains(status));
        assertFalse(response1.contains(content1));
        assertTrue(response2.contains(status));
        assertTrue(response3.contains(status));
        assertTrue(response3.contains(content1));
        assertTrue(response4.contains(status));
        assertTrue(response5.contains(content2));
        assertTrue(response6.contains(status));
        assertFalse(response7.contains(content2));
    }
}