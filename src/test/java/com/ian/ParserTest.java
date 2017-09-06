package com.ian;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void parserReturnsFormattedResponseFromChef() {
        //GIVEN
        String expected =
                "HTTP/1.1 200 OK" +
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
                "<a href=\"/partial_content.txt\">partial_content.txt</a>\n" +
                "<a href=\"/patch-content.txt\">patch-content.txt</a>\n" +
                "<a href=\"/text-file.txt\">text-file.txt</a>" +
                "\n</body>" +
                "\n</html>";
        String request = "GET / HTTP/1.1";
        //WHEN
        String actual = new String(Parser.parse(request, directory));
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void parserIgnoresAdditionalContentPastTheRequestLine() {
        //GIVEN
        byte[] expected = (
                "HTTP/1.1 200 OK" +
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
                "<a href=\"/partial_content.txt\">partial_content.txt</a>\n" +
                "<a href=\"/patch-content.txt\">patch-content.txt</a>\n" +
                "<a href=\"/text-file.txt\">text-file.txt</a>" +
                "\n</body>" +
                "\n</html>").getBytes();
        String request = "GET / HTTP/1.1" +
                         "\nHost: [rsid].112.2o7.net" +
                         "\nX-Forwarded-For: 192.168.10.1";
        //WHEN
        byte[] actual = Parser.parse(request, directory);
        //THEN
        assertArrayEquals(expected, actual);
    }

    @Test
    public void parserHandlesNullRequests() {
        byte[] expected = null;
        byte[] actual = Parser.parse(null, directory);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void parseHeadSetsTheRequestStartLineValues() {
        //GIVEN
        String request = "PUT /basho HTTP/1.1" +
                "\ncontent-type: text/plain\r\n\r\n" +
                "ancient pond\n" +
                "a frog jumps in\r\n\r\n" +
                "sound of water";
        //WHEN
        Parser.parseHead(request);
        //THEN
        assertEquals("PUT", Parser.requestMethod);
        assertEquals("/basho", Parser.requestedUrl);
        assertEquals("HTTP/1.1", Parser.httpVersion);
    }


    @Test
    public void setRequestBodySavesTheBodyOfTheRequestForReference() {
        //GIVEN
        String body = "ancient pond\n" +
                "a frog jumps in\r\n\r\n" +
                "sound of water";
        String request = "PUT /basho HTTP/1.1\n" +
                "content-type: text/plain\r\n\r\n" + body;
        //WHEN
        Parser.setRequestBody(request);
        //THEN
        String actual = Parser.requestBody;
        Parser.resetParser();
        assertEquals(body, actual);

    }

    @Test
    public void applyAppropriateMethodDirectsRequestsByTheirRequestMethod() {
        //GIVEN
        String url = "/the-secret-kingdom-of-shangri-la";
        Parser.directory = directory;
        Parser.requestMethod = "GET";
        Parser.requestedUrl = url;
        Parser.httpVersion = "HTTP/1.1";
        String expected = "HTTP/1.1 404 Not Found\r\n\r\n";
        //WHEN
        String actual = new String(Parser.applyAppropriateMethod());
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void headReturnsOnlyTheStartLineOfGETRequest() {
        //GIVEN
        String url = "/";
        Parser.directory = directory;
        Parser.requestedUrl = url;
        byte[] expected = "HTTP/1.1 200 OK".getBytes();
        //WHEN
        byte[] actual = Parser.head();
        //THEN
        assertArrayEquals(expected, actual);
    }

    @Test
    public void deleteDeletesTheFilesOfDELETERequest() {
        //GIVEN
        String url = "/heart-sutra";
        Parser.directory = directory;
        Parser.requestedUrl = url;
        byte[] emptiness = "".getBytes();
        byte[] substance = "Substance !!= Emptiness; Emptiness !!= Substance.".getBytes();
        FileHelper.setFileBytes(directory, url, substance);
        //WHEN
        Parser.delete();
        //THEN
        byte[] fileSubstance = FileHelper.getFileBytes(directory, url);
        FileHelper.ensureDeletion(directory, url);
        assertArrayEquals(emptiness, fileSubstance);
    }

    @Test
    public void deleteReturns200onSuccessfulDELETERequest() {
       //GIVEN
        String url = "/here-and-gone-again";
        Parser.directory = directory;
        Parser.requestedUrl = url;
        byte[] content = {0};
        FileHelper.setFileBytes(directory, url, content);
        String expectedString = "HTTP/1.1 200 OK";
        //WHEN
        String actualString = new String(Parser.delete());
        //THEN
        FileHelper.ensureDeletion(directory, url);
        assertTrue(actualString.contains(expectedString));
    }

    @Test
    public void getReturnsTheResultsOfGETRequest() {
        //GIVEN
        String url = "/the-lost-city-of-atlantis";
        Parser.directory = directory;
        Parser.requestedUrl = url;
        String expected = "HTTP/1.1 404 Not Found\r\n\r\n";
        //WHEN
        String actual = new String(Parser.get());

        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void getReturnsTheUnSmushedParamsOfGETRequest() {
        //GIVEN
        String url = "/";
        String[] preParsedParams = { "name=Yorick", "jest=infinite", "fancy=most excellent"};
        Parser.directory = directory;
        Parser.requestedUrl = url;
        Parser.params = preParsedParams;
        String name = "name = Yorick";
        String jest = "jest = infinite";
        String fancy = "fancy = most excellent";
        //WHEN
        String actual = new String(Parser.get());
        //THEN
        Parser.resetParser();
        assertTrue(actual.contains(name));
        assertTrue(actual.contains(jest));
        assertTrue(actual.contains(fancy));
    }

    @Test
    public void postReturns200onSuccessfulPOSTRequestWhenFileExists() {
        //GIVEN
        String url = "/basho";
        byte[] content = {0};
        Parser.directory = directory;
        Parser.requestedUrl = url;
        FileHelper.setFileBytes(directory, url, content);
        String expectedString = "HTTP/1.1 200 OK";
        //WHEN
        String actualString = new String(Parser.post());
        //THEN
        FileHelper.ensureDeletion(directory, url);
        assertTrue(actualString.contains(expectedString));
    }

    @Test
    public void postAppendsRequestBodyToExistingFilesOnSuccess() {
        //GIVEN
        String url = "/basho";
        String originalContent = "no sign to tell\n";
        String[] additionalContent = {"in the cicada's cry\n",
                                      "how it will soon die."};
        FileHelper.setFileBytes(directory, url, originalContent.getBytes());
        Parser.directory = directory;
        Parser.requestedUrl = url;
        Parser.params = additionalContent;
        //WHEN
        Parser.post();
        //THEN
        String actualContent = new String(FileHelper.getFileBytes(directory, url));
        FileHelper.ensureDeletion(directory, url);
        Parser.resetParser();
        assertEquals((originalContent + String.join("", additionalContent)), actualContent);
    }

    @Test
    public void putReturns200onSuccessfulPUTRequest() {
        //GIVEN
        String url = "/basho";
        Parser.directory = directory;
        Parser.requestedUrl = url;
        String expectedString = "HTTP/1.1 200 OK";
        //WHEN
        String actualString = new String(Parser.put());
        //THEN
        FileHelper.ensureDeletion(directory, url);
        assertTrue(actualString.contains(expectedString));
    }


    @Test
    public void putOverwritesExistingFilesOnSuccessfulPUTRequest() {
        //GIVEN
        String url = "/basho";
        String[] replacementContent = {"Ah, tranquility!",
                                       "Penetrating the very rock,",
                                       "A cicada’s voice.;"};
        byte[] preExistingContent = ("sick on this journey,\n" +
                                     "my dreams keep flying over\n" +
                                     "the desolate field.").getBytes();
        FileHelper.setFileBytes(directory, url, preExistingContent);
        Parser.directory = directory;
        Parser.requestedUrl = url;
        Parser.params = replacementContent;
        //WHEN
        Parser.put();
        //THEN
        String actualContent = new String(FileHelper.getFileBytes(directory, url));
        FileHelper.ensureDeletion(directory, url);
        assertEquals(String.join("", replacementContent), actualContent);
    }

    @Test
    public void putDoesNotReturn0ContentLengthInResponse() {
        //GIVEN
        String badValue = "Content-Length: 0";
        String url = "/basho";
        String request = "PUT " + url + "?Poverty's%20child%20-%0A" +
                "he%20starts%20to%20grind%20rice,%0A" +
                "and%20gazes%20at%20the%20moon. HTTP/1.1";
        //WHEN
        String response = new String(Parser.parse(request, directory));
        //THEN
        FileHelper.ensureDeletion(directory, url);
        assertFalse(response.contains(badValue));
    }

    @Test
    public void postDoesNotReturn0ContentLengthInResponse() {
        //GIVEN
        String badValue = "Content-Length: 0";
        String url = "/basho";
        String request = "POST " + url + "?Poverty's%20child%20-%0A" +
                "he%20starts%20to%20grind%20rice,%0A" +
                "and%20gazes%20at%20the%20moon. HTTP/1.1";
        FileHelper.setFileBytes(directory, url, "".getBytes());
        //WHEN
        String response = new String(Parser.parse(request, directory));
        //THEN
        FileHelper.ensureDeletion(directory, url);
        assertFalse(response.contains(badValue));
    }

    @Test
    public void get_post_get_put_get_delete_get() {
        //GIVEN
        String fileName = "/form";
        String status = "HTTP/1.1 200 OK";
        String paramDelimiter = "?";
        String content1 = "data=fatcat";
        String content2 = "data=heathcliff";
        String requestBase =  " HTTP/1.1\r\n\r\n";
        String deleteRequest = "DELETE " + fileName + requestBase;
        String getRequest = "GET " + fileName + requestBase;
        String postRequest = "POST " + fileName + paramDelimiter + content1 + requestBase;
        String putRequest = "PUT " + fileName + paramDelimiter + content2 + requestBase;
        //WHEN
        String response1 = new String(Parser.parse(getRequest, directory));
        String response2 = new String(Parser.parse(postRequest, directory));
        String response3 = new String(Parser.parse(getRequest, directory));
        String response4 = new String(Parser.parse(putRequest, directory));
        String response5 = new String(Parser.parse(getRequest, directory));
        String response6 = new String(Parser.parse(deleteRequest, directory));
        String response7 = new String(Parser.parse(getRequest, directory));
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


    @Test
    public void addAllParamsAddsTheRequestBodyToTheParamsStringArray() {
        //GIVEN
        String[] expected = { "one fish", "two fish", "red fish", "blue fish"};
        String[] params = {"two fish", "red fish", "blue fish"};
        Parser.params = params;
        Parser.requestBody = "one fish";
        //WHEN
        String[] actual = Parser.addAllParams();
        //THEN
        assertArrayEquals(expected, actual);
    }

    @Test
    public void resetParserResetsAllPublicStaticVariables() {
        //GIVEN
        String emptyString = "";
        int emptyArrayLength = 0;
        String oldCrustyRequest = "GET /?theMoon=thePondsReflection HTTP/1.1\r\n\r\nfurther content";
        Parser.parse(oldCrustyRequest, directory);
        //WHEN
        Parser.resetParser();
        //THEN
        assertEquals(emptyString, Parser.directory);
        assertEquals(emptyString, Parser.requestMethod);
        assertEquals(emptyString, Parser.requestedUrl);
        assertEquals(emptyArrayLength, Parser.params.length);
        assertEquals(emptyString, Parser.httpVersion);
        assertEquals(emptyString, Parser.requestBody);
    }
}