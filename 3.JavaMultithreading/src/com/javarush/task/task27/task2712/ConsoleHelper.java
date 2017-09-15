package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        String text = in.readLine();
        return text;
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> list = new ArrayList<>();

        writeMessage(Dish.allDishesToString());

        while (true) {
            String text = readString();
            if ("exit".equals(text)) {
                break;
            }

            try {
                Dish dish = Dish.valueOf(text);
                list.add(dish);
            } catch (IllegalArgumentException e) {
                writeMessage("Нет такого блюда");
            }
        }
        return list;
    }
}
