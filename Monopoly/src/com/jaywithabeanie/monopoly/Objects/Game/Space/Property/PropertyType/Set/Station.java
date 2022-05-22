package com.jaywithabeanie.monopoly.Objects.Game.Space.Property.PropertyType.Set;

import com.jaywithabeanie.monopoly.Objects.Game.Space.Property.Property;

import java.util.List;


public class Station extends Property {

    private List<Integer> rent;

    public Station(String name, int cost, List<Integer> rent) {
        super(name, cost);
        this.rent = rent;
    }

    public List<Integer> getRent() {
        return rent;
    }

    public void setRent(List<Integer> rent) {
        this.rent = rent;
    }
}
