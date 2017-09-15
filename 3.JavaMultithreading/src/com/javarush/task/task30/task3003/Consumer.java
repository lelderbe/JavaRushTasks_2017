package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

/**
 * Created by user on 18.05.2017.
 */
public class Consumer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            Thread.currentThread().sleep(450);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!Thread.currentThread().isInterrupted()) {

            try {
                System.out.format("Processing %s\n", queue.take().toString());
            } catch (InterruptedException e) {
            }
        }

    }
}
