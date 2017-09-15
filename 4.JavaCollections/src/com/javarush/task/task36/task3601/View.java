package com.javarush.task.task36.task3601;

/**
 * Created by user on 28.05.2017.
 */
public class View {
    private Model model;

    public View(Model model) {
        this.model = model;
    }

    public void fireEventShowData() {
        System.out.println(model.onDataListShow());
    }

}
