package com.javarush.task.task37.task3702.female;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

/**
 * Created by user on 28.05.2017.
 */
public class FemaleFactory implements AbstractFactory {
    public Human getPerson(int age) {

        Human result = null;

        if (age <= KidGirl.MAX_AGE) {
            result = new KidGirl();
        } else if (age <= TeenGirl.MAX_AGE) {
            result = new TeenGirl();
        } else {
            result = new Woman();
        }

        return result;
    }

}
