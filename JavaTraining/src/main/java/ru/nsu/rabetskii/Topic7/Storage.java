package ru.nsu.rabetskii.Topic7;

import java.util.LinkedList;
import java.util.List;

public class Storage<T> {
    private  List<T> list;
    private int capacity;

    public Storage(int capacity) {
        this.capacity = capacity;
        this.list = new LinkedList<>();
    }

    public synchronized void put(T item) throws InterruptedException {
        while (list.size() >= capacity){
            wait();
        }
        list.add(item);
        notifyAll();
    }

    public synchronized T take() throws InterruptedException {
        while (list.isEmpty()){
            wait();
        }
        T item = list.getLast();
        list.removeLast();
        notifyAll();
        return item;
    }
}
