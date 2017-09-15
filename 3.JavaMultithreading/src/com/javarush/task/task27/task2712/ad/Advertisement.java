package com.javarush.task.task27.task2712.ad;

/**
 * Created by user on 21.05.2017.
 */
public class Advertisement { // implements Comparable<Advertisement> {
    private Object content;
    private String name;
    private long initialAmount;
    private int hits;
    private int duration;

    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        amountPerOneDisplaying = initialAmount / hits;


    }

    public int getHits() {
        return hits;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public void revalidate() {
        if (hits <= 0) {
            throw new UnsupportedOperationException();
        }
        hits--;
    }

//    @Override
//    public int compareTo(Advertisement o) {
//        int result = Long.compare(amountPerOneDisplaying, o.amountPerOneDisplaying);
//        if (result != 0) {
//            return result;
//        }
//
//        return Long.compare(duration, o.duration);
//    }



}
