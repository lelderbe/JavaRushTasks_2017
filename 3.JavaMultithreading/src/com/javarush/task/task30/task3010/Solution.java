package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
//        String number = args[0];

        boolean isCorrect = false;

        for (int i = 2; i < 37; i++) {
            try {
                new BigInteger(args[0], i);
                isCorrect = true;
                System.out.println(i);
                break;
            } catch (Exception ignore) { /*NOP*/ }
        }

        if (!isCorrect) {
            System.out.println("incorrect");
        }
    }
}