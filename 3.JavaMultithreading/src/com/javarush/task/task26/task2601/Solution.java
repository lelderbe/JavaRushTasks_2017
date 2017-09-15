package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(sort(new Integer[] {13, 8, 5, 17})));;
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Arrays.sort(array);

        int mediana;

        if (array.length == 0) {
            return array;
        }

        if (array.length % 2 == 0) {
            // чётное количество
            mediana = (array[array.length / 2] + array[array.length / 2 -1]) / 2;
        } else {
            mediana = array[array.length / 2];
        }

        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.valueOf(Math.abs(o1 - mediana)).compareTo(Integer.valueOf(Math.abs(o2 - mediana)));
            }
        });

        return array;
    }
}
