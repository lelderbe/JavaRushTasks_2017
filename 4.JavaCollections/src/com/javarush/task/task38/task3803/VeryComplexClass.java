package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object[] array = {new String(), new Integer(5)};

        for (int i = 0; i < array.length; i++) {
            System.out.println((String) array[i]);
        }
    }

    public void methodThrowsNullPointerException() {
        String string = null;
        string.length();

    }

    public static void main(String[] args) {

    }
}
