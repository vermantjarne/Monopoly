package com.vermant.monopoly.Objects;

import java.util.List;

public class Player {

    // Player attributes
    private String name;
    private int balance = 1500;
    private List<Property> properties;

    // Constructor
    public Player() {
    }

    // Get: Get name of player
    public String getName() {
        return name;
    }

    // Set: Set name of player
    public void setName(String name) {
        this.name = name;
    }

    /** @Type Getter */
    /** @Info Gets the balance of a player */
    public int getBalance() {
        return balance;
    }

    /** @Type Setter */
    /** @Info Increases the balance of a player */
    public Player increaseBalance(int amount) {
        this.balance = this.balance + amount;
        return this;
    }

    /** @Type Setter */
    /** @Info Decreases the balance of a player */
    public Player decreaseBalance(int amount) {
        this.balance = this.balance - amount;
        return this;
    }

    // Get: Get properties of player
    public List<Property> getProperties() {
        return properties;
    }

    /** @Type Checker */
    /** @Info Checks whether the player has a property */
    public boolean hasProperty(Property property) {
        return getProperties().contains(property);
    }

    /** @Type Setter */
    /** @Info Adds a property to the player's properties */
    public void addProperty(Property property) {
        properties.add(property);
    }

    /** @Type Setter */
    /** @Info Removes a property from the player's properties */
    public void removeProperty(Property property) {
        properties.remove(property);
    }

}