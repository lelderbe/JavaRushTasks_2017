package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Path fileName = Paths.get(args[0]);
        Path zipFile = Paths.get(args[1]);
//        Path fileName = Paths.get("C:/TEMP/1/file1.txt");
//        Path zipFile = Paths.get("C:/TEMP/1/1.zip");

        Map<String, byte[]> map = new LinkedHashMap<>();

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile.toString()))) {
            ZipEntry zipEntry;

            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                for (int c = zipInputStream.read(); c != -1; c = zipInputStream.read()) {
                        byteArrayOutputStream.write(c);
                }

                zipInputStream.closeEntry();
                map.put(zipEntry.getName(), byteArrayOutputStream.toByteArray());
            }
        }


        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile.toString()))) {

            boolean fileExists = false;

            for (String key : map.keySet()) {
                if (key.endsWith(fileName.getFileName().toString())) {
                    fileExists = true;
                    break;
                }
            }

            if (!fileExists) {
                zipOutputStream.putNextEntry(new ZipEntry("new/" + fileName.getFileName().toString()));
                Files.copy(fileName, zipOutputStream);
                zipOutputStream.closeEntry();
            }

            for (Map.Entry<String, byte[]> entry : map.entrySet()) {
                zipOutputStream.putNextEntry(new ZipEntry(entry.getKey()));
                if (fileExists && entry.getKey().endsWith(fileName.getFileName().toString())) {
                    Files.copy(fileName, zipOutputStream);
                } else {
                    zipOutputStream.write(entry.getValue());
                }
                zipOutputStream.closeEntry();
            }
        }

    }
}
