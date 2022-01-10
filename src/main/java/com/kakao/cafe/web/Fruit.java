package com.kakao.cafe.web;

public class Fruit {

    private String name;
    private int sugar;

    public Fruit(String name, int sugar) {
        this.name = name;
        this.sugar = sugar;
    }

    public String getName() {
        return name;
    }

    public int getSugar() {
        return sugar;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", sugar=" + sugar +
                '}';
    }
}
