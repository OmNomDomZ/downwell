package ru.nsu.rabetskii;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Reader {

        HashMap<String, Integer> map = new HashMap<String, Integer>();
    public void ReadFile() {
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
    }

    public void WriteFile() {
        for (String name : map.keySet())
        {
            System.out.println(name + " " + map.get(name).toString());
        }
    }


}
