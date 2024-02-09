package ru.nsu.rabetskii;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 0) {
            ReadFile reader = new ReadFile();
            for (String name : args){
                reader.FileReader(name);
            }
        }
//        else{
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            String str = reader.readLine();
//        }
    }
}