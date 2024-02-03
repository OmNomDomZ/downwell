package ru.nsu.rabetskii;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Writer {
    private double GetFrequencyOfWord(final Integer num, final Integer NumOfWords){
        return ((double) (num * 100) / NumOfWords);
    }

    public void CSVWriter(final HashMap<String, Integer> map, final Integer NumOfWords) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("file.csv"))) {
            writer.write("Слово,Частота,Частота (в %)");
            writer.newLine();

            for (String name : map.keySet()) {
                Integer num = map.get(name);
                double frequency = GetFrequencyOfWord(num, NumOfWords);
//                String result = String.format("%.3f", frequency);
                writer.write(name + "," + num.toString() + "," + frequency + "%");
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
