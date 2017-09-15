package com.javarush.task.task28.task2807;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* 
Знакомство с ThreadPoolExecutor
*/
public class Solution {

    public static class MyThread implements Runnable {
        private int id;

        public MyThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            doExpensiveOperation(id);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //Add your code here
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

        for (int i = 1; i < 11; i++) {
            queue.add(new MyThread(i));
        }

        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 1000, TimeUnit.MILLISECONDS, queue);

        pool.prestartAllCoreThreads();

        pool.shutdown();

        pool.awaitTermination(5, TimeUnit.SECONDS);


        /* output example
pool-1-thread-2, localId=2
pool-1-thread-3, localId=3
pool-1-thread-1, localId=1
pool-1-thread-3, localId=5
pool-1-thread-2, localId=4
pool-1-thread-3, localId=7
pool-1-thread-1, localId=6
pool-1-thread-3, localId=9
pool-1-thread-2, localId=8
pool-1-thread-1, localId=10
         */
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId=" + localId);
    }
}
