package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {

        String pattern = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        boolean hasSmallLetters = false;
        boolean hasCapitalLetters = false;
        boolean hasDigits = false;
        int length = 0;
        ByteArrayOutputStream byteArrayOutputStream = null;

        while (!(hasSmallLetters && hasCapitalLetters && hasDigits)) {
            hasSmallLetters = false;
            hasCapitalLetters = false;
            hasDigits = false;
            length = 0;

            byteArrayOutputStream = new ByteArrayOutputStream();

            while (length < 8) {
                char randomSymbol = pattern.charAt((int) (Math.random() * pattern.length()));

                hasSmallLetters = Character.isLowerCase(randomSymbol) ? true : hasSmallLetters;
                hasCapitalLetters = Character.isUpperCase(randomSymbol) ? true : hasCapitalLetters;
                hasDigits = Character.isDigit(randomSymbol) ? true : hasDigits;

                byteArrayOutputStream.write(randomSymbol);
                length++;
            }

        }

        return byteArrayOutputStream;
    }
}