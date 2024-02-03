package ru.nsu.rabetskii;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Reader {
    public HashMap<String, Integer> sortMap(HashMap<String,Integer> map) {
        List<Map.Entry<String, Integer> > list =
                new LinkedList<>(map.entrySet());

        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> var1,
                               Map.Entry<String, Integer> var2) {
                return (var2.getValue()).compareTo(var1.getValue());
            }
        });

        HashMap<String, Integer> newMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            newMap.put(aa.getKey(), aa.getValue());
        }
        return newMap;
    }


    public HashMap<String, Integer> ReadFile() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        try (BufferedReader reader = new BufferedReader(new FileReader("file"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] WordArray = line.split("(?U)\\W+");
                for (String word : WordArray)
                {
                    Integer n = map.get(word.toLowerCase());
                    n = (n == null) ? 1 : ++n;
                    map.put(word.toLowerCase(), n);
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
