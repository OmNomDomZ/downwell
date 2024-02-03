package ru.nsu.rabetskii;


import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Reader reader = new Reader();
        HashMap<String, Integer> map = reader.ReadFile();

        System.out.println(map);

    }
}