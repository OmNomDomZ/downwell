package ru.nsu.rabetskii;

import java.util.HashMap;


public class Linker {
    private HashMap<String, Integer> map = new HashMap<>();
    private Integer numOfWords = 0;

    public void LinkerCall(String name) {
        try{
            Reader reader = new Reader();
            map = reader.ReadFile(name);
            numOfWords = reader.getNumOfWords();
        }
        catch (Exception e){
            System.err.println("Произошла ошибка при чтении файла: " + e.getMessage());
            return;
        }

        try{
            Writer writer = new Writer();
            writer.CSVWriter(map, numOfWords, name);
        }
        catch (Exception e){
            System.err.println("Произошла ошибка при записи файла: " + e.getMessage());
        }

    }
}
