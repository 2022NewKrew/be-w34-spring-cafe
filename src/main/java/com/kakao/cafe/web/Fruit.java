package com.kakao.cafe.web;

public class Fruit {

    private final String name;
    private final int sugar;

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

    public void validate() {
        if (name == null || name.length() < 2 || sugar < 0) {
            throw new IllegalArgumentException("Fail to validate Fruit");
        }
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", sugar=" + sugar +
                '}';
    }
}
