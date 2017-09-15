package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String filename = in.readLine();
//        String filename = "C:/TEMP/1/file123.txt";

//        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        BufferedReader file = new BufferedReader(new FileReader(filename));

        String[] words = file.readLine().split(" ");
        Arrays.sort(words);

        //...
        StringBuilder result = getLine(words);
        System.out.println(result.toString());

        in.close();
    }

    public static StringBuilder getLine(String... words) {
        if (words.length == 0) {
            return new StringBuilder();
        }

        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(words));

        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0));
        list.remove(0);

        int count = 0;
        label:
        while (list.size() > 0) {
            char letter = sb.toString().toLowerCase().charAt(sb.length() - 1);

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).toLowerCase().charAt(0) == letter) {
                    sb.append(" ");
                    sb.append(list.get(i));
                    count++;
                    list.remove(i);
                    continue label;
                }
            }
            break;
        }

        return sb;
    }
}
