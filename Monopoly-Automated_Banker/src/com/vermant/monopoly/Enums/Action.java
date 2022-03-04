package com.vermant.monopoly.Enums;

import com.vermant.monopoly.Objects.Game;
import com.vermant.monopoly.Objects.Player;
import com.vermant.monopoly.Objects.Property;
import com.vermant.monopoly.Utils.ConsoleColors;


public class Action {

    /** @Type Attributes */
    private ActionType actionType;
    private Player player;
    private Property property;

    public Action(Player player, ActionType actionType) {
        this.player = player;
        this.actionType = actionType;
    }

    public Action(Player player, Property property, ActionType actionType) {
        this.player = player;
        this.property = property;
        this.actionType = actionType;
    }

    public ActionType getActionType() {
        return this.actionType;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Property getProperty() {
        return this.property;
    }

    public boolean canBuyProperty() {

        // Error: Property owned by a player
        if (property.getOwner() == null) {
            System.out.println(ConsoleColors.RED_BOLD + "ERROR " + ConsoleColors.RESET + "This property is already owned by a player!");
            return false;
        }

        // Error: Player balance < property cost
        if (player.getBalance() < this.property.getCost()) {
            System.out.println(ConsoleColors.RED_BOLD + "ERROR " + ConsoleColors.RESET + "You do not own the money to buy this property!");
            return false;
        }

        return true;

    }

    public static void buyProperty(Player player, Property property) {
        player.addProperty(property)
              .decreaseBalance(property.getCost());
    }

    public static void passByGO(Player player) {
        player.increaseBalance(200);
    }

    public static void getOutOfJail(Player player) {
        player.decreaseBalance(50);
    }

}
