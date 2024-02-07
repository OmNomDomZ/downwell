package ru.nsu.rabetskii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 0) {
            for (String name : args){
                CommandLoader loader = new CommandLoader();
                String[] commands = loader.load(name);
                FileProcessing fileProcessing = new FileProcessing();

            }

        }
        else{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String str = reader.readLine();
        }
    }
//        Factory factory = new Factory();
//        factory.factory();
}