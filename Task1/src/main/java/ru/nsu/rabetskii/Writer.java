package ru.nsu.rabetskii;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Writer {
    public void CSVWriter(final HashMap<String, Integer> map) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("file.csv"))) {

        }
    }
}
