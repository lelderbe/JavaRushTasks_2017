package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.Observer;

/**
 * Created by user on 20.05.2017.
 */
public class Restaurant {
    public static void main(String[] args) {

        Cook cook = new Cook("Amigo");
        Tablet tablet = new Tablet(5);
        tablet.addObserver(cook);
        tablet.createOrder();
        Waiter waiter = new Waiter();
        cook.addObserver(waiter);

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printActiveVideoSet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printArchivedVideoSet();
        directorTablet.printCookWorkloading();

    }
}
