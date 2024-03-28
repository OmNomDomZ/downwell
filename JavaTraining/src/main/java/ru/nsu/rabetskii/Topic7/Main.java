package ru.nsu.rabetskii.Topic7;

public class Main {
    public static void main(String[] args) {
        int N = 10;
        int P = 3;
        int C = 3;

        Storage<String> storage = new Storage<>(N);

        Thread[] producers = new Thread[P];
        for (int i = 0; i < P; ++i){
            producers[i] = new Thread (new Producer(storage, i));
            producers[i].start();
        }

        Thread[] consumers = new Thread[C];
        for (int i = 0; i < C; ++i){
            consumers[i] = new Thread((new Consumer(storage, i)));
            consumers[i].start();
        }


    }
}