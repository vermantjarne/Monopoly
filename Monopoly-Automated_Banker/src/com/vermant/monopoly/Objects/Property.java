package com.vermant.monopoly.Objects;

public class Property {

    // Property attributes
    private String name;
    private int cost;

    // Constructor
    public Property(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    // Getter: Get name of property
    public String getName() {
        return name;
    }

    // Getter: Get cost of property
    public int getCost() {
        return cost;
    }

}
