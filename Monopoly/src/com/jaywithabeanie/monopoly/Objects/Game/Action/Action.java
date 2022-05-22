package com.jaywithabeanie.monopoly.Objects.Game.Action;


public class Action {

    /** @Type Attributes */
    private ActionType actionType;

    public Action(ActionType actionType) {
        this.actionType = actionType;
    }

    public ActionType getActionType() {
        return this.actionType;
    }

}
