package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(in.readLine());
        }

        int minIndex = 0;
        int maxIndex = 0;
        for (int i = 1; i < 10; i++) {
            String str = list.get(i);
            if (str.length() < list.get(minIndex).length()) {
                minIndex = i;
            }
            if (str.length() > list.get(maxIndex).length()) {
                maxIndex = i;
            }
        }

        if (minIndex < maxIndex) {
            System.out.println(list.get(minIndex));
        } else {
            System.out.println(list.get(maxIndex));
        }

    }
}
