package com.diegoamezquita.portfolio.models;

public class App {

    private String name;
    private int drawable;

    public App(String name, int drawable) {
        this.name = name;
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public int getDrawable() {
        return drawable;
    }
}
