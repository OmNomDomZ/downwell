package ru.nsu.rabetskii.lab7;

public class Producer implements Runnable {
    private Storage<String> storage;
    private int id;

    public Producer(Storage<String> storage, int id){
        this.storage = storage;
        this.id = id;
    }

    @Override
    public void run() {
        try  {
            for (int i = 1; i < 5; ++i){
                String item = "p" + id + "-" + i;
                storage.put(item);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
