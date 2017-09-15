package com.javarush.task.task28.task2805;

/**
 * Created by user on 15.05.2017.
 */
public class MyThread extends Thread {
    private static int currentPriority = Thread.MIN_PRIORITY;

    {
            if (this.getThreadGroup().getMaxPriority() > currentPriority) {
                this.setPriority(currentPriority);
            } else {
                this.setPriority(this.getThreadGroup().getMaxPriority());
            }

            currentPriority++;
            if (currentPriority > Thread.MAX_PRIORITY) {
                currentPriority = Thread.MIN_PRIORITY;
            }
    }

    public MyThread() {
    }

    public MyThread(Runnable target) {
        super(target);
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
    }

    public MyThread(String name) {
        super(name);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
    }

}
