package com.jaywithabeanie.monopoly.Objects.Game.Action;

public enum ActionType {

    //PAY_JAIL_BAIL("Pay the jail bail"),

    // ALWAYS
    DECLARE_BANKRUPTCY("Declare bankruptcy", true),

    // TURN_START
    ROLL_DICE("Roll the dice", true),

    // TURN_MOVED
    CONFIRM("Confirm", true),

    PAY_TAXES("Pay taxes", true),
    RECEIVE_MONEY("Receive money", false),

    DRAW_CARD("Draw a card", false),
    CONFIRM_CARD_ACTION("Confirm", true),

    GO_TO_JAIL("Confirm", true),
    PAY_JAIL_FINE("Pay a $50 fine", true);

    /** @Type Property actions */
    //BUY_PROPERTY("Buy property"),
    //PAY_RENT("Pay rent"),

    /** @Type Limiting actions */
    /** @Info The player is forced to undertake these actions when they occur, yet they can still do other things */
    //END_TURN("End your turn"),

    /** @Type Forced actions */
    /** @Info The player is forced to undertake these actions when they occur and can't do anything else */
    //DRAW_A_CHANCE_CARD("Draw a chance card"),
    //DRAW_A_COMMUNITY_CHEST_CARD("Draw a community chest card");


    private String text;
    private boolean progressesTurn;

    ActionType(String text, boolean progressesTurn) {
        this.text = text;
        this.progressesTurn = progressesTurn;
    }

    public String getText() {
        return this.text;
    }

    public boolean progressesTurn() {
        return this.progressesTurn;
    }

}
