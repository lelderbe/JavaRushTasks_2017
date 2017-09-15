package com.javarush.task.task06.task0606;

import java.io.*;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.valueOf(in.readLine());

        while (a > 0) {
            if (a % 2 == 0) {
                even++;
            } else {
                odd++;
            }
            a = a / 10;
        }

        System.out.printf("Even: %d Odd: %d\n", even, odd);

    }
}
