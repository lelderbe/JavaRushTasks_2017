package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

/**
 * Created by user on 18.05.2017.
 */
public class Producer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        if (!Thread.currentThread().isInterrupted()) {

            for (int i = 1; i < 10; i++) {
                System.out.format("Элемент 'ShareItem-%d' добавлен\n", i);
                queue.offer(new ShareItem("ShareItem-" + i, i));

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (queue.hasWaitingConsumer()) {
                    System.out.format("Consumer в ожидании!\n");
                }

            }
        }
    }
}
