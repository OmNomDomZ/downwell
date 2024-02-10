package ru.nsu.rabetskii;

import java.util.HashMap;
import java.util.Map;


public class Linker {
    private Map<String, Integer> map = new HashMap<>();
    private Integer numOfWords = 0;

    public void linkerCall(String name) {
        try{
            Reader reader = new Reader();
            map = reader.readFile(name);
            numOfWords = reader.getNumOfWords();
        }
        catch (Exception e){
            System.err.println("Произошла ошибка при чтении файла: " + e.getMessage());
            return;
        }

        try{
            Writer writer = new Writer();
            writer.csvWriter(map, numOfWords, name);
        }
        catch (Exception e){
            System.err.println("Произошла ошибка при записи файла: " + e.getMessage());
        }

    }
}
