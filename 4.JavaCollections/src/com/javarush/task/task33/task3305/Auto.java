package com.javarush.task.task33.task3305;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Auto {

    @JsonIgnore
    protected String name;
    @JsonIgnore
    protected String owner;
    @JsonIgnore
    protected int age;
}