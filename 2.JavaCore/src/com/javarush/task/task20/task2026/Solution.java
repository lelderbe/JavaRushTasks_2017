package com.javarush.task.task20.task2026;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/*
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a) {
        Set<Rectangle> list = new HashSet<>();

        // Последовательно проходим по всем ячейкам матрицы.
        // При нахождении элемента прямоугольника - проверям, нет ли уже этого прямоугольника в списке.
        // Если нет - сразу же определяем размеры прямоугольника (конечные индексы) и заносим его в список.
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] == 1) { //найден элемент
                    if (isInList(list, i, j)) { // уже в списке
                        continue;
                    }
                    // поиск прямоугольника
                    list.add(getRectangleFrom(a, i, j));
                }
            }
        }

//        System.out.println(list);

        return list.size();
    }

    // Проверка, принадлежит ли данная ячейка прямоугольнику в нашем списке
    private static boolean isInList(Set<Rectangle> list, int x, int y) {
        for (Rectangle rectangle : list) {
            if (x >= rectangle.x1 && x <= rectangle.x2 && y >= rectangle.y1 && y <= rectangle.y2) {
                return true;
            }
        }
        return false;
    }

    // Определение координат прямоугольника
    private static Rectangle getRectangleFrom(byte[][] a, int x1, int y1) {
        int columns = a.length;
        int rows = a[0].length;

        int x2 = x1;
        int y2 = y1;

        while ((x2 + 1) < columns && (a[x2 + 1][y1] != 0)) {
            x2++;
        }

        while ((y2 + 1) < rows && (a[x2][y2 + 1] != 0)) {
            y2++;
        }

        return new Rectangle(x1, y1, x2, y2);
    }

    // Класс прямоугольника
    public static class Rectangle {
        int x1, y1;
        int x2, y2;

        public Rectangle(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public String toString() {
            return String.format("[%d, %d]-[%d, %d]", x1, y1, x2, y2);
        }
    }

}
