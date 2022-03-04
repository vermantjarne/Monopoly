package com.vermant.monopoly.Objects;

public class Property {

    /** @Type Attributes */
    /** @Info Contains the name of the property */
    private final String name;
    /** @Info Contains the cost of the property */
    private final int cost;
    /** @Info Contains the owner of the property */
    private Player owner;

    /** @Type Constructor */
    /** @Info Constructs a new Property instance */
    public Property(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    /** @Type Getter */
    /** @Info Gets the name of the property */
    public String getName() {
        return name;
    }

    /** @Type Getter */
    /** @Info Gets the cost of the property */
    public int getCost() {
        return cost;
    }

    /** @Type Getter */
    /** @Info Gets the owner of the property */
    public Player getOwner() {
        return this.owner;
    }

    /** @Type Setter */
    /** @Info Sets the owner of the property */
    public Property setOwner(Player player) {
        this.owner = player;
        return this;
    }

}
