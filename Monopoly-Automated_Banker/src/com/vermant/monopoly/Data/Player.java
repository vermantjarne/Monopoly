package com.vermant.monopoly.Data;

import java.util.List;

public class Player {

    // Player attributes
    private String name;
    private Token token;
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

    // Get: Get token of player
    public Token getToken() {
        return token;
    }

    // Set: Set token of player
    public void setToken(Token token) {
        this.token = token;
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
