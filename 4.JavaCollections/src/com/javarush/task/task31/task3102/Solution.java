package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> list = new ArrayList<>();
        LinkedList<File> queue = new LinkedList<>();

        queue.offer(new File(root));

        while (!queue.isEmpty()) {
            for (File file : queue.poll().listFiles()) {
                if (file.isFile()) {
                    list.add(file.getAbsolutePath());
                } else {
                    queue.offer(file);
                }
            }
        }

        return list;

    }

    public static void main(String[] args) throws IOException {
        System.out.println(getFileTree("C:/TEMP/11"));
    }
}
