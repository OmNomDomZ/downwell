package ru.nsu.rabetskii;

import java.util.HashMap;

public class Linker {
    public void LinkerCall(String name) {
        Reader reader = new Reader();
        HashMap<String, Integer> map = reader.ReadFile(name);
        final Integer NumOfWords = reader.getNumOfWords();

        Writer writer = new Writer();
        writer.CSVWriter(map, NumOfWords, name);
    }


}
