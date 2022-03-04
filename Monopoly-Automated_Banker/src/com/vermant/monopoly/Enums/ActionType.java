package com.vermant.monopoly.Enums;

public enum ActionType {

    // Pre dice roll
    DECLARE_BANKRUPTCY("Declare yourself bankrupt"),
    PAY_JAIL_BAIL("Pay the jail bail"),

    // Dice roll
    ROLL_DICE("Roll the dice"),

    // Post dice roll
    BUY_PROPERTY("Buy property"),
    PAY_RENT("Pay rent");

    private String text;

    ActionType(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

}
