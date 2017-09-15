package com.javarush.task.task27.task2707;

import java.util.concurrent.locks.*;

/* 
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isNormalLockOrder(final Solution solution, final Object o1, final Object o2) throws Exception {
        //do something here
        Thread t1 = new Thread() {
            @Override
            public void run() {
                solution.someMethodWithSynchronizedBlocks(o1, o2);
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                synchronized (o1) {
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        synchronized (o2) {
            t1.start();
            Thread.sleep(100);
//            System.out.println(t1.getState());
            t2.start();
            Thread.sleep(100);
//            System.out.println(t2.getState());
            if (t2.getState() == Thread.State.BLOCKED) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isNormalLockOrder(solution, o1, o2));
    }
}
