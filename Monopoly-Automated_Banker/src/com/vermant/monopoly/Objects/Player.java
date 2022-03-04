package com.vermant.monopoly.Objects;

import com.vermant.monopoly.Enums.Action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Player {

    /** @Type Attributes */
    /** @Info Contains the name of the player */
    private String name;
    /** @Info Contains the balance of the player */
    private int balance = 1500;
    // TODO @Info
    private int timesRolledDouble = 0;
    /** @Info Contains a list of the properties of the player */
    private ArrayList<Property> properties = new ArrayList<>();

    /** @Type Constructor */
    /** @Info Constructs a new Property instance */
    public Player(String name) {
        this.name = name;
    }

    /** @Type Getter */
    /** @Info Gets the name of the player */
    public String getName() {
        return name;
    }

    /** @Type Getter */
    /** @Info Gets the balance of the player */
    public int getBalance() {
        return balance;
    }

    /** @Type Setter */
    /** @Info Increases the balance of the player */
    public Player increaseBalance(int amount) {
        this.balance = this.balance + amount;
        return this;
    }

    /** @Type Setter */
    /** @Info Decreases the balance of the player */
    public Player decreaseBalance(int amount) {
        this.balance = this.balance - amount;
        return this;
    }

    /** @Type Getter */
    /** @Info Gets the properties of the player */
    public ArrayList<Property> getProperties() {
        return properties;
    }

    /** @Type Checker */
    /** @Info Checks whether the player has a property */
    public boolean hasProperty(Property property) {
        return property.getOwner() == this;
    }

    /** @Type Setter */
    /** @Info Adds a property to the player's properties */
    public Player addProperty(Property property) {
        properties.add(property);
        return this;
    }

    /** @Type Setter */
    /** @Info Removes a property from the player's properties */
    public Player removeProperty(Property property) {
        properties.remove(property);
        return this;
    }

    public Player increaseTimesRolledDoubled() {
        this.timesRolledDouble++;
        return this;
    }

    public Player resetTimesRolledDoubled() {
        this.timesRolledDouble = 0;
        return this;
    }

    public Player startTurn() {
        this.resetTimesRolledDoubled();
        return this;
    }

    public Player endTurn() {
        return this;
    }

    public Object performAction(Action action) {
        switch (action.getActionType()) {
            case ROLL_DICE -> {
                Random random = new Random();
                return random.nextInt(1, 7) + random.nextInt(1, 7);
            }
        }
        return this;
    }

    public boolean canPerformAction(Action action) {

        return true;

    }

}
