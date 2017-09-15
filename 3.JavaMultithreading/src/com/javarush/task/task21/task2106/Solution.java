package com.javarush.task.task21.task2106;

import java.util.Date;
import java.util.HashSet;

/* 
Ошибка в equals/hashCode
*/
public class Solution {
    private int anInt;
    private String string;
    private double aDouble;
    private Date date;
    private Solution solution;

    public Solution(int anInt, String string, double aDouble, Date date, Solution solution) {
        this.anInt = anInt;
        this.string = string;
        this.aDouble = aDouble;
        this.date = date;
        this.solution = solution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solution)) return false;

        Solution solution1 = (Solution) o;

        if (Double.compare(solution1.aDouble, aDouble) != 0) return false;
        if (anInt != solution1.anInt) return false;
        if (date != null ? !date.equals(solution1.date) : !(solution1.date == null)) return false;
        if (solution != null ? !solution.equals(solution1.solution) : !(solution1.solution == null)) return false;
        if (string != null ? !string.equals(solution1.string) : !(solution1.string == null)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        long temp;

//        result = anInt;
        result = 37 * result + (int) anInt;
        temp = aDouble != +0.0d ? Double.doubleToLongBits(aDouble) : 0L;
        result = 37 * result + (int) (temp - (temp >>> 32));
        result = 37 * result + (solution != null ? solution.hashCode() : 0);
        result = 37 * result + (string == null ? 0 : string.hashCode());
        result = 37 * result + (date == null ? 0 : date.hashCode());
        return result;
    }

    public static void main(String[] args) {
        Solution solution1 = new Solution(1, "a", 0.5, new Date(), null);
        Solution solution2 = new Solution(1, "a", 0.5, new Date(), null);

        System.out.println(solution1.equals(solution2));

        HashSet<Solution> set = new HashSet<>();
        set.add(solution1);

        System.out.println(set.contains(solution2));

        System.out.println(solution1.hashCode());
        System.out.println(solution2.hashCode());
    }
}
