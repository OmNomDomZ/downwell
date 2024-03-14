    package ru.nsu.rabetskii.lab7;

    public class Consumer implements Runnable{
        private Storage<String> storage;
        private int id;

        public Consumer(Storage<String> storage, int id){
            this.storage = storage;
            this.id = id;
        }

        @Override
        public void run() {
            try{
                while(true){
                    String item = storage.take();
                    System.out.println("c" + id + " consumes " + item);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
