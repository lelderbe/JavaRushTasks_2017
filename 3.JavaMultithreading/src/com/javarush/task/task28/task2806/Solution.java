package com.javarush.task.task28.task2806;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* 
Знакомство с Executors
*/
public class Solution {

    public static class MyThread implements Runnable {

        private int i;
        public MyThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            doExpensiveOperation(i);
        }
    }

    public static volatile int x = 1;
    public static void main(String[] args) throws InterruptedException {
        //Add your code here
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 1; i < 11; i++) {
            executorService.submit((Runnable) new MyThread(i));
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    doExpensiveOperation(x++);
//                }
//            });
        }

        executorService.shutdown();

        executorService.awaitTermination(5, TimeUnit.SECONDS);

        /* output example
pool-1-thread-2, localId=2
pool-1-thread-1, localId=1
pool-1-thread-3, localId=3
pool-1-thread-1, localId=7
pool-1-thread-1, localId=9
pool-1-thread-4, localId=4
pool-1-thread-5, localId=5
pool-1-thread-2, localId=6
pool-1-thread-1, localId=10
pool-1-thread-3, localId=8
         */
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId="+localId);
    }
}
