package ru.nsu.rabetskii;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Reader {
    private final HashMap<String, Integer> map = new HashMap<String, Integer>();
    private Integer numOfWords = 0;

    private HashMap<String, Integer> sortMap(HashMap<String,Integer> map) {
        List<Map.Entry<String, Integer> > list =
                new LinkedList<>(map.entrySet());

        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> var1,
                               Map.Entry<String, Integer> var2) {
                return (var2.getValue()).compareTo(var1.getValue());
            }
        });

        HashMap<String, Integer> newMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> sortedVar : list) {
            newMap.put(sortedVar.getKey(), sortedVar.getValue());
        }
        return newMap;
    }

    public final Integer getNumOfWords() {
        return numOfWords;
    }

    public HashMap<String, Integer> ReadFile() {
        try (Scanner reader = new Scanner(new FileReader("file"))) {
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
        catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<String, Integer> newMap = sortMap(map);
        return newMap;
    }
}
