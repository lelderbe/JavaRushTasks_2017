package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Path resultFileName = Paths.get(args[0]);
        Set<String> fileNamePart = new TreeSet<>();

        for (int i = 1; i < args.length; i++) {
            fileNamePart.add(args[i]);
        }

        List<InputStream> list = new ArrayList<>();

        for (String file : fileNamePart) {
            list.add(new FileInputStream(file));
        }

        System.out.println(fileNamePart);

        // Output stream to file
        FileOutputStream fileOutputStream = new FileOutputStream(resultFileName.toString());

        ByteArrayOutputStream byteArrayOutputStream = null;
        ZipEntry zipEntry = null;
        ZipInputStream zipInputStream = null;

                zipInputStream = new ZipInputStream(new SequenceInputStream(Collections.enumeration(list)));
                while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                    System.out.println(zipEntry.getName() + " / " + zipEntry.getSize());

//                    byteArrayOutputStream = new ByteArrayOutputStream();
//
//                    for (int c = zipInputStream.read(); c != -1; c = zipInputStream.read()) {
//                        byteArrayOutputStream.write(c);
//                    }

//                    Files.copy(zipInputStream, resultFileName, StandardCopyOption.REPLACE_EXISTING);
//                    Files.write(resultFileName, byteArrayOutputStream.toByteArray(), StandardOpenOption.APPEND);

                    byte[] buffer = new byte[1024];
                    int count = 0;
                    while ((count = zipInputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, count);
                        fileOutputStream.flush();
                    }
                }

//            } catch (EOFException e) {
//                System.out.println("111111111");
//                Files.write(resultFileName, byteArrayOutputStream.toByteArray(), StandardOpenOption.APPEND);
//                zipInputStream.closeEntry();
//                zipInputStream.close();
//            }
        zipInputStream.close();
        fileOutputStream.close();
    }
}
