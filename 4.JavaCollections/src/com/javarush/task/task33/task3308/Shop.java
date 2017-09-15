package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by user on 24.05.2017.
 */
@XmlRootElement
public class Shop {

    public Goods goods;
    public int count;
    public double profit;

    public String[] secretData;

    static class Goods {
        public List<String> names;

    }
}
