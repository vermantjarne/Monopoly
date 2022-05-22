package com.jaywithabeanie.monopoly.Objects.Game.Space.Tax;

import com.jaywithabeanie.monopoly.Objects.Game.Space.Space;


public class Tax extends Space {

    private int tax;

    public Tax(int tax) {
        this.tax = tax;
    }

    public int getTax() {
        return this.tax;
    }



}
