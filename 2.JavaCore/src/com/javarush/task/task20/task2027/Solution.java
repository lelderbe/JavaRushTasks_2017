package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<>();

        for (String word : words) { // по каждому слову
            nextWord:
            for (int i = 0; i < crossword.length; i++) { // перебор всех ячеек массива
                for (int j = 0; j < crossword[i].length; j++) {
                    if (crossword[i][j] == word.charAt(0)) {
                        for (Direction direction : Direction.values()) { // перебор всех направлений
                            if (checkDirection(crossword, i, j, direction, word.substring(1))) {
                                // found
                                Word item = new Word(word);
                                item.setStartPoint(j, i);
                                item.setEndPoint(j + direction.getDy() * (word.length() - 1), i + direction.getDx() * (word.length() - 1));
                                list.add(item);
                                break nextWord;
                            }
                        }
                    }
                }
            }
        }

//        System.out.println(list);

        return list;
    }

    public static boolean checkDirection(int[][] array, int x, int y, Direction direction, String word) {

        int newX = x + direction.getDx();
        int newY = y + direction.getDy();
        //check bounds
        boolean checkBounds = newX >=0 && newX < array.length && newY >=0 && newY < array[newX].length;

        boolean result = checkBounds && array[newX][newY] == word.charAt(0);

        if (!result) {
            return false;
        }

        if (word.length() == 1) {
            return true;
        } else {
            return checkDirection(array, newX, newY, direction, word.substring(1));
        }

    }

    public enum Direction {
        NW (-1, -1),
        N (0, -1),
        NE (1, -1),
        W (-1, 0),
        E (1, 0),
        SW (-1, 1),
        S (0, 1),
        SE (1, 1);

        private final int dx;
        private final int dy;

        int getDx() {
            return dx;
        }

        int getDy() {
            return dy;
        }

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
