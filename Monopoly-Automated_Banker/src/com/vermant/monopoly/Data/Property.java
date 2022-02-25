package com.vermant.monopoly.Data;

public class Property {

    // Property attributes
    private String name;
    private int cost;

    // Constructor
    public Property(int cost) {
        this.cost = cost;
    }

    // Setter: Set name of property
    public void setName(String name) {
        this.name = name;
    }

    // Getter: Get name of property
    public String getName() {
        return name;
    }

    // Setter: Set costs of property
    public void setCost(int cost) {
        this.cost = cost;
    }

    // Getter: Get cost of property
    public int getCost() {
        return cost;
    }

}
