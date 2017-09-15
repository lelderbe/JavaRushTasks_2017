package com.javarush.task.task36.task3601;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 28.05.2017.
 */
public class Model {

    public List<String> getData() {
        List<String> data = new ArrayList<String>() {{
            add("First string");
            add("Second string");
            add("Third string");
        }};
        return data;
    }


    public List<String> getStringDataList() {
        return getData();
    }

    public List<String> onDataListShow() {
        return getStringDataList();
    }

}
