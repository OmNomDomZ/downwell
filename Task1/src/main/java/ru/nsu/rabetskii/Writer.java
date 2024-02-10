package ru.nsu.rabetskii;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

public class Writer {
    private String getFrequencyOfWord(final Integer num, final Integer numOfWords){
        double freq = (double) (num * 100) / numOfWords;
        return new DecimalFormat("#0.000").format(freq);
    }

    public void csvWriter(final Map<String, Integer> map, final Integer numOfWords, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".csv"))) {
            writer.write("Слово,Частота,Частота (в %)");
            writer.newLine();

            for (String name : map.keySet()) {
                Integer num = map.get(name);
                String frequency = getFrequencyOfWord(num, numOfWords);
                frequency = "\"" + frequency + "%\"";
                writer.write(name + "," + num.toString() + "," + frequency);
                writer.newLine();
            }
        }
    }
}
