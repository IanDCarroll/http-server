package com.ian;

import org.junit.Test;

import static org.junit.Assert.*;

public class MethodTest {
    public static final String directory =
            System.getProperty("user.dir") + "/public";

    @Test
    public void headReturnsOnlyTheHeadOfARequest() {
        //GIVEN
        String expected = "HTTP/1.1 200 OK" +
                "\nContent-Length: 0" +
                "\nContent-Type: text/html" +
                "\r\n\r\n";
        String url = "/";
        //WHEN
        String actual = new String(Method.head(directory, url));
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void deleteDeletesTheFilesOfDELETERequest() {
        //GIVEN
        byte[] expectedEmptiness = "".getBytes();
        String url = "/heart-sutra";
        byte[] substance = "Substance !!= Emptiness; Emptiness !!= Substance.".getBytes();
        FileHelper.setFileBytes(directory, url, substance);
        //WHEN
        Method.delete(directory, url);
        //THEN
        byte[] fileSubstance = FileHelper.getFileBytes(directory, url);
        FileHelper.ensureDeletion(directory, url);
        assertArrayEquals(expectedEmptiness, fileSubstance);
    }

    @Test
    public void deleteReturns200onSuccessfulDELETERequest() {
        //GIVEN
        String expectedString = "HTTP/1.1 200 OK";
        String url = "/here-and-gone-again";
        byte[] content = {};
        FileHelper.setFileBytes(directory, url, content);
        //WHEN
        String actualString = new String(Method.delete(directory, url));
        //THEN
        FileHelper.ensureDeletion(directory, url);
        assertTrue(actualString.contains(expectedString));
    }

    @Test
    public void getReturnsTheResultsOfGETRequest() {
        //GIVEN
        String expected = "HTTP/1.1 404 Not Found";
        String url = "/the-lost-city-of-atlantis";
        String[] emptyParams = {};
        //WHEN
        String actual = new String(Method.get(directory, url, emptyParams));
        //THEN
        assertTrue(actual.contains(expected));
    }

    @Test
    public void postReturns200onSuccessfulPOSTRequestWhenFileExists() {
        //GIVEN
        String expectedString = "HTTP/1.1 200 OK";
        String url = "/basho";
        byte[] content = {};
        FileHelper.setFileBytes(directory, url, content);
        String emptyBody = "";
        String[] emptyParams = {};
        //WHEN
        String actualString = new String(Method.post(directory, url, emptyBody, emptyParams));
        //THEN
        FileHelper.ensureDeletion(directory, url);
        assertTrue(actualString.contains(expectedString));
    }

    @Test
    public void postAppendsRequestBodyToExistingFilesOnSuccess() {
        //GIVEN
        String originalContent = "no sign to tell\n";
        String bodyContent = "in the cicada's cry\n";
        String[] paramContent = {"how it will soon die."};
        String expected = originalContent + bodyContent + String.join("", paramContent);
        String url = "/basho";
        FileHelper.setFileBytes(directory, url, originalContent.getBytes());
        //WHEN
        Method.post(directory, url, bodyContent, paramContent);
        //THEN
        String actual = new String(FileHelper.getFileBytes(directory, url));
        FileHelper.ensureDeletion(directory, url);
        assertEquals(expected, actual);
    }

    @Test
    public void postDoesNotCreateFilesThatDontExist() {
        //GIVEN
        Boolean expected = false;
        String url = "/basho";
        String replacementContent = "Ah, tranquility!\n" +
                "Penetrating the very rock,\n" +
                "A cicada’s voice.";
        String[] emptyParams = {};
        //WHEN
        Method.post(directory, url, replacementContent, emptyParams);
        //THEN
        boolean actual = FileHelper.checkExistence(directory, url);
        FileHelper.ensureDeletion(directory, url);
        assertEquals(expected, actual);
    }

    @Test
    public void putCreatesFilesThatDontYetExist() {
        //GIVEN
        Boolean expected = true;
        String url = "/basho";
        String replacementContent = "Ah, tranquility!\n" +
                "Penetrating the very rock,\n" +
                "A cicada’s voice.";
        String[] emptyParams = {};
        //WHEN
        Method.put(directory, url, replacementContent, emptyParams);
        //THEN
        boolean actual = FileHelper.checkExistence(directory, url);
        FileHelper.ensureDeletion(directory, url);
        assertEquals(expected, actual);
    }

    @Test
    public void putOverwritesExistingFilesOnSuccessfulPUTRequest() {
        //GIVEN
        String replacementContent = "Ah, tranquility!\n" +
                "Penetrating the very rock,\n" +
                "A cicada’s voice.";
        String expected = String.join("", replacementContent);
        String url = "/basho";
        byte[] preExistingContent = ("sick on this journey,\n" +
                "my dreams keep flying over\n" +
                "the desolate field.").getBytes();
        String[] emptyParams = {};
        FileHelper.setFileBytes(directory, url, preExistingContent);
        //WHEN
        Method.put(directory, url, replacementContent, emptyParams);
        //THEN
        String actual = new String(FileHelper.getFileBytes(directory, url));
        FileHelper.ensureDeletion(directory, url);
        assertEquals(expected, actual);
    }

    @Test
    public void putReturns200onSuccessfulPUTRequest() {
        //GIVEN
        String expectedString = "HTTP/1.1 200 OK";
        String url = "/basho";
        String emptyBody = "";
        String[] emptyParams = {};
        //WHEN
        String actualString = new String(Method.put(directory, url, emptyBody, emptyParams));
        //THEN
        FileHelper.ensureDeletion(directory, url);
        assertTrue(actualString.contains(expectedString));
    }

}