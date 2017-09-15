package com.javarush.task.task25.task2506;

/**
 * Created by user on 24.04.2017.
 */
public class LoggingStateThread extends Thread {
    private Thread thread;
    private State state;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        state = thread.getState();
        System.out.println(state);
        while(true) {
            if (thread.getState() != state) {
                state = thread.getState();
                System.out.println(state);
            }
            if (state == State.TERMINATED) {
                break;
            }
        }
    }
}
