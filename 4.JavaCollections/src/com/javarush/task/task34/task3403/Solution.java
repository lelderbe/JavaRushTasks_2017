package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public static void main(String[] args) {
        new Solution().recursion(132);
    }

    public void recursion(int n) {

        int a = 2;
        while (a <= n) {
            if ((n % a) == 0) {
                if (a != n) {
                    System.out.print(a + " ");
                    recursion(n / a);
                } else {
                    System.out.print(a);
                }
                return;
            }
            a++;
        }


//        if (n > 1) {
//            if (n % 2 == 0) {
//                System.out.print("2 ");
//                recursion(n / 2);
//            } else if (n % 3 == 0) {
//                System.out.print("3 ");
//                recursion(n / 3);
//            } else {
//                System.out.println(n);
//            }
//        }
    }
}
