package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 12.04.2017.
 */
public class Hippodrome {

    public static Hippodrome game;

    private List<Horse> horses;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }

        for (int i = 0; i < 6; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        Horse winner = null;
        for (Horse horse : horses) {
            if (winner == null || winner.getDistance() < horse.getDistance()) {
                winner = horse;
            }
        }

        return winner;
    }

    public void printWinner() {
        System.out.printf("Winner is %s!", getWinner());
    }

    public static void main(String[] args) {

        game = new Hippodrome(new ArrayList<>());
        Horse horse1 = new Horse("Forward", 3, 0);
        Horse horse2 = new Horse("Hurricane", 3, 0);
        Horse horse3 = new Horse("Wind", 3, 0);

        game.getHorses().add(horse1);
        game.getHorses().add(horse2);
        game.getHorses().add(horse3);

        game.run();

        game.printWinner();
    }
}
