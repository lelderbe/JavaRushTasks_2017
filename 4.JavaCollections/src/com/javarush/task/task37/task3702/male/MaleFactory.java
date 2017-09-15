package com.javarush.task.task37.task3702.male;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

/**
 * Created by user on 28.05.2017.
 */
public class MaleFactory implements AbstractFactory {
    public Human getPerson(int age) {

        Human result = null;

        if (age <= KidBoy.MAX_AGE) {
            result = new KidBoy();
        } else if (age <= TeenBoy.MAX_AGE) {
            result = new TeenBoy();
        } else {
            result = new Man();
        }

        return result;
    }
}
