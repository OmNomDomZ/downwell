package ru.nsu.rabetskii;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        HashMap<String, Integer> map = reader.ReadFile();
        final Integer NumOfWords = reader.getNumOfWords();

        Writer writer = new Writer();
        writer.CSVWriter(map, NumOfWords);
    }
}