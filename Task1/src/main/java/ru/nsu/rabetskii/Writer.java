package ru.nsu.rabetskii;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;

public class Writer {
    private String GetFrequencyOfWord(final Integer num, final Integer NumOfWords){
        double freq = (double) (num * 100) / NumOfWords;
        return new DecimalFormat("#0.000").format(freq);
    }

    public void CSVWriter(final HashMap<String, Integer> map, final Integer NumOfWords) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("file.csv"))) {
            writer.write("Слово,Частота,Частота (в %)");
            writer.newLine();

            for (String name : map.keySet()) {
                Integer num = map.get(name);
                String frequency = GetFrequencyOfWord(num, NumOfWords);
                frequency = "\"" + frequency + "%\"";
                writer.write(name + "," + num.toString() + "," + frequency);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
