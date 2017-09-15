package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {
    public static long filesSize = 0;
    public static int countFolders = 0;
    public static int countFiles = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String name = in.readLine();

        in.close();


        if (!Files.isDirectory(Paths.get(name))) {
            System.out.println(name + " - не папка");
//            System.exit(0);
        } else {
            Files.walkFileTree(Paths.get(name), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    countFiles++;
                    filesSize += Files.size(file);

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    countFolders++;
                    return FileVisitResult.CONTINUE;
                }
            });


            System.out.println("Всего папок - " + (countFolders - 1));
            System.out.println("Всего файлов - " + countFiles);
            System.out.println("Общий размер - " + filesSize);

        }


    }
}
