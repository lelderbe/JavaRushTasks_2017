package com.javarush.task.task32.task3210;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {

        String filename = args[0];
        long number = Long.valueOf(args[1]);
        String text = args[2];

        String textFromFile;
        byte[] buffer = new byte[text.length()];


        RandomAccessFile file = new RandomAccessFile(filename, "rw");

        file.seek(number);

        file.read(buffer, 0, buffer.length);

        textFromFile = new String(buffer);


        String result = "false";
        if (textFromFile.equals(text)) {
            result = "true";
        }

        file.seek(file.length());

        file.write(result.getBytes());

        file.close();



    }

//    public static String convertByteToString(byte readBytes[]) {
//        return System.Text.Encoding.UTF8.GetString(readBytes);
//    }
}
