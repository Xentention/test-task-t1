package com.testtaskt1.stringprocessing;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class StringCountFreqHelper implements Callable<Map<String, Integer>> {
    private final String string;

    public StringCountFreqHelper(String string) {
        this.string = string;
    }

    /**
     * Считает количество вхождений в строку для каждого символа
     * @return Map<String, Integer> количество вхождений в формате ключ-значение
     */
    @Override
    public Map<String, Integer> call() {
        Map<String, Integer> result = new HashMap<>();
        if(string.isEmpty())
            return result;

        String[] entries = string.split("");
        for(String entry : entries){
            if(result.containsKey(entry))
                result.put(entry, result.get(entry) + 1);
            else
                result.put(entry, 1);
        }

        return result;
    }
}
