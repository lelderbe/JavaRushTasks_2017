package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Dish;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;
import javafx.beans.InvalidationListener;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by user on 20.05.2017.
 */
public class Tablet extends Observable {
    public static Logger logger = Logger.getLogger(Tablet.class.getName());

    final int number;

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {

        Order order = null;

        try {
//            List<Dish> dishes = ConsoleHelper.getAllDishesForOrder();
            order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }


        if (order != null && !order.isEmpty()) {
            setChanged();
            notifyObservers(order);

            try {
                AdvertisementManager advertisementManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
                advertisementManager.processVideos();
                StatisticManager.getInstance().register(new VideoSelectedEventDataRow(advertisementManager.getBestAds(), 0, 0));
            } catch (NoVideoAvailableException e) {
                logger.log(Level.INFO, "No video is available for the order " + order);
            }
        }

        return order;

    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }

}
