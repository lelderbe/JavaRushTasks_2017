package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < 10; i++) {
            list.add(Integer.valueOf(in.readLine()));
        }

        int temp = 1;
        int value = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) != value) {
                if (temp > count) {
                    count = temp;
                }

                temp = 1;
                value = list.get(i);
            } else {
                temp++;
            }
        }

        if (temp > count) {
            count = temp;
        }

        System.out.println(count);

    }
}