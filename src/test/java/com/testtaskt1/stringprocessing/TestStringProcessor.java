package com.testtaskt1.stringprocessing;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class TestStringProcessor {
    @Test
    void testExampleString(){
        String testString = "aaaaabcccc";
        Map<String, Integer> trueResult = new HashMap<>();
        trueResult.put("a", 5);
        trueResult.put("b", 1);
        trueResult.put("c", 4);
        Map<String, Integer> testMap = new HashMap<>();
        try {
            testMap = StringProcessor.countCharacterFrequencies(testString);
        } catch (ExecutionException | InterruptedException e) {
            fail("testEmptyString finished with an Exception");
        }
        assertEquals(trueResult, testMap);
    }

    @Test
    void testMultiThread(){
        //560 - a, 6 - c, 6 - b, 2 - k
        String testString = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaakaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbb" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaakaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbb";
        Map<String, Integer> trueResult = new HashMap<>();
        trueResult.put("a", 560);
        trueResult.put("b", 6);
        trueResult.put("c", 6);
        trueResult.put("k", 2);
        Map<String, Integer> testMap = new HashMap<>();
        try {
            testMap = StringProcessor.countCharacterFrequencies(testString);
        } catch (ExecutionException | InterruptedException e) {
            fail("testEmptyString finished with an Exception");
        }
        assertEquals(trueResult, testMap);
    }

    @Test
    void testEmptyString(){
        String testString = "";
        Map<String, Integer> testMap = new HashMap<>();
        try {
            testMap = StringProcessor.countCharacterFrequencies(testString);
        } catch (ExecutionException | InterruptedException e) {
            fail("testEmptyString finished with an Exception");
        }
        assertTrue(testMap.isEmpty());
    }
}
