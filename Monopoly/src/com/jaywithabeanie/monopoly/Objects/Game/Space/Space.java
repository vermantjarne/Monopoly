package com.jaywithabeanie.monopoly.Objects.Game.Space;

import com.jaywithabeanie.monopoly.Objects.Game.Action.Action;
import com.jaywithabeanie.monopoly.Objects.Game.Space.Card.Card;
import com.jaywithabeanie.monopoly.Objects.Game.Space.Card.Chance.Chance;
import com.jaywithabeanie.monopoly.Objects.Game.Space.Card.CommunityChest.CommunityChest;
import com.jaywithabeanie.monopoly.Objects.Game.Space.FreeParking.FreeParking;
import com.jaywithabeanie.monopoly.Objects.Game.Space.Go.Go;
import com.jaywithabeanie.monopoly.Objects.Game.Space.GoToJail.GoToJail;
import com.jaywithabeanie.monopoly.Objects.Game.Space.Tax.Tax;
import com.jaywithabeanie.monopoly.Objects.Game.Space.Tax.TaxType.IncomeTax;
import com.jaywithabeanie.monopoly.Objects.Game.Space.Tax.TaxType.LuxuryTax;

import javax.swing.*;


public class Space {

    private Action action;

    public Space() {

    }

    public Action getAction() {
        return this.action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public SpaceType getSpaceType() {

        if (this instanceof Go ) {
            return SpaceType.GO;
        }

        if (this instanceof GoToJail) {
            return SpaceType.GO_TO_JAIL;
        }

        if (this instanceof FreeParking) {
            return SpaceType.FREE_PARKING;
        }

        if (this instanceof Tax) {

            if (this instanceof IncomeTax) {
                return SpaceType.INCOME_TAX;
            }

            if (this instanceof LuxuryTax) {
                return SpaceType.LUXURY_TAX;
            }

        }

        if (this instanceof Card) {

            if (this instanceof Chance) {
                return SpaceType.CHANCE;
            }

            if (this instanceof CommunityChest) {
                return SpaceType.COMMUNITY_CHEST;
            }

        }

        return SpaceType.RAILROAD;

    }

}