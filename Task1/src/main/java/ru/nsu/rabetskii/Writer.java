package ru.nsu.rabetskii;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;

public class Writer {
    private String GetFrequencyOfWord(final Integer num, final Integer numOfWords){
        double freq = (double) (num * 100) / numOfWords;
        return new DecimalFormat("#0.000").format(freq);
    }

    public void CSVWriter(final HashMap<String, Integer> map, final Integer numOfWords, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".csv"))) {
            writer.write("Слово,Частота,Частота (в %)");
            writer.newLine();

            for (String name : map.keySet()) {
                Integer num = map.get(name);
                String frequency = GetFrequencyOfWord(num, numOfWords);
                frequency = "\"" + frequency + "%\"";
                writer.write(name + "," + num.toString() + "," + frequency);
                writer.newLine();
            }
        }
    }
}
