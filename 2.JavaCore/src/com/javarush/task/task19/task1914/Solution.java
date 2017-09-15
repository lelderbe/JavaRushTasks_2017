package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        //запоминаем настоящий PrintStream в специальную переменную
        PrintStream consoleStream = System.out;

        //Создаем динамический массив
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //создаем адаптер к классу PrintStream
        PrintStream stream = new PrintStream(outputStream);
        //Устанавливаем его как текущий System.out
        System.setOut(stream);

        testString.printSomething();

        //Преобразовываем записанные в наш ByteArray данные в строку
        String result = outputStream.toString().trim();

        String[] strings = result.split(" ");
        // 0 - 1й аргумент
        // 1 - операция: +, -, *
        // 2 - 2й аргумент
        // 3 - =

        int value = 0;
        switch (strings[1].toCharArray()[0]) {
            case '+':
                value = Integer.valueOf(strings[0]) + Integer.valueOf(strings[2]);
                break;
            case '-':
                value = Integer.valueOf(strings[0]) - Integer.valueOf(strings[2]);
                break;
            case '*':
                value = Integer.valueOf(strings[0]) * Integer.valueOf(strings[2]);
                break;
            default:
                break;
        }


        //Возвращаем все как было
        System.setOut(consoleStream);

        System.out.println(result + " " + value);

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

