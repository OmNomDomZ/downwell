package ru.nsu.rabetskii;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Reader {
    private final Map<String, Integer> map = new HashMap<>();
    private Integer numOfWords = 0;

    public final Integer getNumOfWords() {
        return numOfWords;
    }

    private Map<String, Integer> sortMap(Map<String,Integer> map) {
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(map.entrySet());

        list.sort(new Comparator<>() {
            public int compare(Map.Entry<String, Integer> var1,
                               Map.Entry<String, Integer> var2) {
                return (var2.getValue()).compareTo(var1.getValue());
            }
        });

        Map<String, Integer> newMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> sortedVar : list) {
            newMap.put(sortedVar.getKey(), sortedVar.getValue());
        }
        return newMap;
    }

    public Map<String, Integer> readFile(String fileName) throws IOException {
        try (Scanner reader = new Scanner(new FileReader(fileName))) {
            String line;
            while (reader.hasNext()) {
                line = reader.nextLine();
                String[] WordArray = line.split("(?U)\\W+");
                numOfWords += WordArray.length;
                for (String word : WordArray)
                {
                    if (!word.isEmpty()){
                        Integer n = map.get(word.toLowerCase());
                        n = n == null ? 1 : ++n;
                        map.put(word.toLowerCase(), n);
                    }
                }
            }
        }
        return sortMap(map);
    }
}
