package ru.nsu.rabetskii.Topic9;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int N = 3;
    private static final int M = 5;

    public static void main(String args[]) {
        CyclicBarrier barrier = new CyclicBarrier(N);
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < N; ++i){
            final int threadNum = i + 1;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < M; ++j){
                        try{
                            System.out.println(String.valueOf(threadNum) + '-' + (j + 1));
                            barrier.await();
                        } catch (BrokenBarrierException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
        }
        executor.shutdown();
    }
}
