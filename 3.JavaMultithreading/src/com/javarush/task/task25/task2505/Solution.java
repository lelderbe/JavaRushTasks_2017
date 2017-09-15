package com.javarush.task.task25.task2505;

/* 
Без дураков
*/
public class Solution {



    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new Solution().new MyThread("super secret key");
        myThread.start();
//        Thread.sleep(1500);
    }

    public class MyThread extends Thread {

        private class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

            public MyUncaughtExceptionHandler() {
            }

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                e.printStackTrace();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                System.out.println(String.format("%s, %s, %s", secretKey, t.getName(), e.getMessage()));
                e.printStackTrace();

            }
        }

        private String secretKey;

        public MyThread(String secretKey) {

            this.secretKey = secretKey;
            setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
//            setDaemon(true);
        }

        @Override
        public void run() {
            throw new NullPointerException("it's an example");
        }
    }


}

