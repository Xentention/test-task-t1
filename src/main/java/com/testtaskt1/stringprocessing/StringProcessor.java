package com.testtaskt1.stringprocessing;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class StringProcessor {

    /**
     * Многопоточно считает количество вхождений в строку для каждого символа
     *
     * @param string входные данные
     * @return Map<String, Integer> количество вхождений в формате ключ-значение, отсортировано по убыванию
     * @throws ExecutionException   если было выброшено исключение во время вычислений
     * @throws InterruptedException если один из потоков был прерван
     */
    public static Map<String, Integer> countCharacterFrequencies(String string)
            throws ExecutionException, InterruptedException {
        if (string.length() == 0)
            return new HashMap<>();

        int subStringLen = 100;
        int threadsNum = string.length() / subStringLen + 1;

        ExecutorService threadPool = Executors.newFixedThreadPool(threadsNum);
        List<Future<Map<String, Integer>>> results = new ArrayList<>();

        for (int i = 0; i < threadsNum - 1; i++) {
            results.add(threadPool.submit(new StringCountFreqHelper(
                    string.substring(subStringLen * i, subStringLen * i + 100))));
        }
        results.add(threadPool.submit(new StringCountFreqHelper(
                string.substring(subStringLen * (threadsNum - 1)))));

        Map<String, Integer> entries = new HashMap<>();
        for (Future<Map<String, Integer>> result : results) {
            Map<String, Integer> resultMap = result.get();
            resultMap.forEach((key, value) -> entries.merge(key, value, Integer::sum));
        }

        threadPool.shutdown();
        return entries.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }
}
