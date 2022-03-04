package com.vermant.monopoly.Objects;

import java.util.List;


public class Set {

    /** @Type Attributes */
    /** @Info Contains the color of the set */
    private final String color;
    /** @Info Contains the properties of the set */
    private final List<Property> properties;

    public Set(String color, List<Property> properties) {
        this.color = color;
        this.properties = properties;
    }

    public String getColor() {
        return this.color;
    }

    public List<Property> getProperties() {
        return this.properties;
    }

}
