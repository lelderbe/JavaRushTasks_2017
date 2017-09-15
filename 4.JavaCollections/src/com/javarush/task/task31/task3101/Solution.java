package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.*;

/*
Проход по дереву файлов
*/
public class Solution {
    public static TreeSet<File> Lower50 = new TreeSet<>();
    public static void main(String[] args) throws IOException {
//        String path = "C:/TEMP/11/";
//        String resultFileAbsolutePath = "C:/TEMP/12/res.txt";
/*
        String path = args[0];
        String resultFileAbsolutePath = args[1];

        File outputFile = new File(resultFileAbsolutePath);
        outputFile.createNewFile();

        File newOutputFile = new File(outputFile.getParent() + "/" + "allFilesContent.txt");

        FileUtils.renameFile(outputFile, newOutputFile);
*/


        File path = new File(args[0]); //Путь к директории
        File resultFileAbsolutePath = new File(args[1]); //Файл с контекстом всех файлом <50
        File allFilesContent = new File(resultFileAbsolutePath.getParent()+"/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);


//        try (FileWriter fileWriter = new FileWriter(newOutputFile)) {
        try (FileOutputStream fos = new FileOutputStream(allFilesContent)) {

            deepSearch(path);
            TreeMap<String, File> fileAndPath =  new TreeMap<>();
            for(File f: Lower50)
                fileAndPath.put(f.getName(),f);
            for(Map.Entry<String, File> pair: fileAndPath.entrySet()){
                BufferedReader bufferedReader = new BufferedReader(new FileReader(pair.getValue()));
                String s = "";
                while((s = bufferedReader.readLine()) != null)
                    fos.write((s+"\n").getBytes());
                // fos.write("\n".getBytes());
                bufferedReader.close();
            }


/*
            List<File> list = new ArrayList<>();
            Map<String, File> map = new TreeMap<>();

            for (File file : getFiles(new File(path).listFiles())) {
                if (file.length() > 50) {
                    FileUtils.deleteFile(file);
                } else {
                    list.add(file);
                }
            }

            for (File file : list) {
                map.put(file.getName(), file);
            }

            Collections.sort(list);

//            Collections.sort(list, new Comparator<File>() {
//                @Override
//                public int compare(File o1, File o2) {
//                    return o1.getName().compareTo(o2.getName());
//                }
//            });


            for (File file : list) {
//            for (Map.Entry<String, File> entry : map.entrySet()) {
//                FileReader fileReader = new FileReader(entry.getValue());
                FileReader fileReader = new FileReader(file);

                while (fileReader.ready()) {
                    fileWriter.write(fileReader.read());
                }

//                fileWriter.write('\n');
                fileWriter.write(System.lineSeparator());

                fileReader.close();
            }
*/

//                fileWriter.close();
        }

    }

    public static List<File> getFiles(File[] files) {
        List<File> list = new ArrayList<>();

        for (File file : files) {
            if (file.isFile()) {
                list.add(file);
            } else {
                list.addAll(getFiles(file.listFiles()));
//                getFiles(file.listFiles());
            }
        }

        return list;
    }


    public static void deepSearch(File f){
        if(f.isDirectory()){
            for(File ff: f.listFiles()){
                deepSearch(ff);
            }
        }
        else if(f.isFile()){
            if(f.length() > 50)
                FileUtils.deleteFile(f);
            else
                Lower50.add(f);
        }
    }


    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }
}
