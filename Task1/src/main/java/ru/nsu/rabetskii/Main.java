package ru.nsu.rabetskii;

public class Main {
    public static void main(String[] args) {
        if (args.length != 0)
        {
            for (String name : args) {
                Linker linker = new Linker();
                linker.LinkerCall(name);
            }
        }
        else
        {
            System.out.println("no arguments were given");
        }
    }
}