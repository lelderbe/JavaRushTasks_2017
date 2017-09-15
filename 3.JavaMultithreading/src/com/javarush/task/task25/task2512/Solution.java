package com.javarush.task.task25.task2512;

/* 
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        print(e);
        System.out.println(e);
    }

    private void print(Throwable e) {
        if (e.getCause() != null) {
            print(e.getCause());
            System.out.println(e.getCause());
        }
    }

    public static void main(String[] args) throws Exception {

        Thread.currentThread().setUncaughtExceptionHandler(new Solution());

        throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
    }
}
