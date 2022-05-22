package com.jaywithabeanie.monopoly.Objects.Game.Space.Property.PropertyType.Set;

import com.jaywithabeanie.monopoly.Objects.Game.Space.Property.Property;

import java.util.List;


public class Street extends Property {

    private Set set;
    private int houses = 0;
    private List<Integer> rent;

    public Street( Set set, String name, int cost, List<Integer> rent) {
        super(name, cost);
        this.set = set;
        this.rent = rent;
    }

    public int getHouses() {
        return houses;
    }

    public void setHouses(int houses) {
        this.houses = houses;
    }

    public List<Integer> getRent() {
        return rent;
    }

    public void setRent(List<Integer> rent) {
        this.rent = rent;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

}
