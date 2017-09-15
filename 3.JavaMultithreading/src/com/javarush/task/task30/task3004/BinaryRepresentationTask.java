package com.javarush.task.task30.task3004;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Created by user on 18.05.2017.
 */
public class BinaryRepresentationTask extends RecursiveTask<String> {
    private int x;

    public BinaryRepresentationTask(int x) {
        this.x = x;
    }

    @Override
    protected String compute() {

        int a = x % 2;
        int b = x / 2;
        String result = "";


        List<BinaryRepresentationTask> subTasks = new ArrayList<>();

        if (b > 0) {
            BinaryRepresentationTask task = new BinaryRepresentationTask(b);
            task.fork(); // запустим асинхронно
            subTasks.add(0, task);
        }

        for(BinaryRepresentationTask task : subTasks) {
            result += task.join(); // дождёмся выполнения задачи и прибавим результат
        }

        return result + String.valueOf(a);

    }
}
