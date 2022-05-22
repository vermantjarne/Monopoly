package com.jaywithabeanie.monopoly.Objects.Game.Player;

import com.jaywithabeanie.monopoly.Objects.Game.Action.Action;
import com.jaywithabeanie.monopoly.Objects.Game.Action.ActionType;
import com.jaywithabeanie.monopoly.Objects.Game.Frame.Frame;
import com.jaywithabeanie.monopoly.Objects.Game.Game;
import com.jaywithabeanie.monopoly.Objects.Game.Player.PlayerInfo.PlayerStatus;
import com.jaywithabeanie.monopoly.Objects.Game.Player.PlayerInfo.Token;
import com.jaywithabeanie.monopoly.Objects.Game.Space.Property.Property;
import com.jaywithabeanie.monopoly.Objects.Game.Space.Space;
import com.jaywithabeanie.monopoly.Objects.Game.Space.SpaceType;
import com.jaywithabeanie.monopoly.Objects.Game.Space.Tax.Tax;
import com.jaywithabeanie.monopoly.Utils.ConsoleColors;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Player {

    private Game game;
    /** @Type Attributes */
    /** @Info Contains the name of the player */
    private String name;
    // TODO Add token
    private Token token;
    /** @Info Contains the balance of the player */
    private int balance = 1500;
    // TODO Add player status
    private PlayerStatus status;
    private boolean inJail;
    private Action forcedAction;
    // TODO @Info
    private int space = 0;
    // TODO @Info
    private boolean rolledDoubles = false;
    private int timesRolledDoubles = 0;
    private int turnsInJail = 0;
    /** @Info Contains a list of the properties of the player */
    private ArrayList<Property> properties = new ArrayList<>();

    /** @Type Constructor */
    /** @Info Constructs a new Property instance */
    public Player(Game game, String name, Token token) {
        this.game = game;
        this.name = name;
        this.token = token;
    }

    public Game getGame() {
        return this.game;
    }

    /** @Type Getter */
    /** @Info Gets the name of the player */
    public String getName() {
        return this.name;
    }

    public Token getToken() {
        return this.token;
    }

    /** @Type Getter */
    /** @Info Gets the balance of the player */
    public int getBalance() {
        return this.balance;
    }

    /** @Type Setter */
    /** @Info Increases the balance of the player */
    public Player increaseBalance(int amount) {
        this.balance = this.balance + amount;
        // Change text label
        {
            JLabel label = Frame.balances.get(this);
            label.setText(" " + this.getName() + ": $" + this.getBalance());
        }
        return this;
    }

    /** @Type Setter */
    /** @Info Decreases the balance of the player */
    public Player decreaseBalance(int amount) {
        this.balance = this.balance - amount;
        // Change text label
        {
            JLabel label = Frame.balances.get(this);
            label.setText(" " + this.getName() + ": $" + this.getBalance());
        }
        return this;
    }

    public PlayerStatus getStatus() {
        return this.status;
    }

    public Player setStatus(PlayerStatus status) {
        this.status = status;

        if (status == PlayerStatus.TURN_START) {
            JLabel label_player = Frame.balances.get(this);
            label_player.setForeground(Color.DARK_GRAY);
        }

        if (status == PlayerStatus.IDLE) {
            JLabel label_player = Frame.balances.get(this);
            label_player.setForeground(Color.GRAY);
        }

        return this;
    }


    public boolean isInJail() {
        return this.inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
        if (inJail) {
            this.moveToSpace(10);
        }
        // TODO Change location of token
    }


    public boolean hasForcedAction() {
        return this.forcedAction != null;
    }

    public Action getForcedAction() {
        return this.forcedAction;
    }

    public void setForcedAction(Action action) {
        this.forcedAction = action;
    }


    public int getSpace() {
        return this.space;
    }

    public Player moveSpaces(int amount) {

        // Move spaces
        for (int i = 0; i < amount; i++) {

            // Move 1 space
            this.space += 1;

            // Determine player number
            int playerNumber = 0;
            for (int j = 0; j < this.game.getPlayers().size(); j++) {
                if (this.game.getPlayers().get(j) == this) {
                    playerNumber = j;
                }
            }

            JLabel label_player = Frame.players.get(this);

            // Change UI position
            if (Frame.spaceLocations.containsKey(this.space)) {
                Dimension dimension = Frame.spaceLocations.get(this.space).get(playerNumber);
                label_player.setLocation(dimension.width, dimension.height);
            }
            else {
                if (this.space < 10) {
                    label_player.setLocation(label_player.getX() - 65, label_player.getY());
                } else if (this.space < 20) {
                    label_player.setLocation(label_player.getX(), label_player.getY() - 65);
                } else if (this.space < 30) {
                    label_player.setLocation(label_player.getX() + 65, label_player.getY());
                } else {
                    label_player.setLocation(label_player.getX(), label_player.getY() + 65);
                }
            }

            try {
                Thread.sleep(500);
            } catch ( InterruptedException ignored ) {}

            // Increase balance when player lands on Go
            if (this.space == 40) {
                this.increaseBalance(200);
                this.space %= 40;
            }

        }

        if (this.getGame().getSpaces().get(this.getSpace()).getSpaceType() == SpaceType.GO_TO_JAIL) {
            this.performAction(new Action(ActionType.GO_TO_JAIL));
        }

        // Return player
        return this;

    }

    public Player moveToSpace(int space) {

        this.space = space;

        // Determine player number
        int playerNumber = 0;
        for (int j = 0; j < this.game.getPlayers().size(); j++) {
            if (this.game.getPlayers().get(j) == this) {
                playerNumber = j;
                if (this.isInJail()) {
                    playerNumber += 4;
                }
                break;
            }
        }

        JLabel label_player = Frame.players.get(this);

        // Change UI position
        if (Frame.spaceLocations.containsKey(this.space)) {
            Dimension dimension = Frame.spaceLocations.get(this.space).get(playerNumber);
            label_player.setLocation(dimension.width, dimension.height);
        }

        return this;
    }

    /** @Type Getter */
    /** @Info Gets the properties of the player */
    public ArrayList<Property> getProperties() {
        return this.properties;
    }

    /** @Type Checker */
    /** @Info Checks whether the player has a property */
    public boolean hasProperty(Property property) {
        return property.getOwner() == this;
    }

    /** @Type Setter */
    /** @Info Adds a property to the player's properties */
    public Player addProperty(Property property) {
        properties.add(property);
        return this;
    }

    /** @Type Setter */
    /** @Info Removes a property from the player's properties */
    public Player removeProperty(Property property) {
        properties.remove(property);
        return this;
    }

    public Player setRolledDoubles(Boolean bool) {
        this.rolledDoubles = bool;
        return this;
    }

    public boolean hasRolledDoubles() {
        return this.rolledDoubles;
    }

    public int getTimesRolledDoubles() {
        return this.timesRolledDoubles;
    }

    public Player increaseTimesRolledDoubles() {
        this.timesRolledDoubles++;
        return this;
    }

    public Player resetTimesRolledDoubles() {
        this.timesRolledDoubles = 0;
        return this;
    }


    public int getTurnsInJail() {
        return this.turnsInJail;
    }

    public Player increaseTurnsInJail() {
        this.turnsInJail++;
        return this;
    }

    public Player resetTurnsInJail() {
        this.turnsInJail = 0;
        return this;
    }


    public Player startTurn() {
        if (this.getStatus() != PlayerStatus.TURN_MOVED) {
            this.setStatus(PlayerStatus.TURN_START);
        }
        return this;
    }

    public Player endTurn() {

        // Change Player status
        if (this.getStatus() != PlayerStatus.DEFEATED) this.setStatus(PlayerStatus.IDLE);

        // Reset double rolls
        this.resetTimesRolledDoubles();

        // Change current player
        int playerNumber = this.game.getPlayers().indexOf(this);
        while (true) {

            // Up the counter by 1 or set it to 0
            playerNumber += 1;
            if (playerNumber >= this.game.getPlayers().size()) playerNumber = 0;

            // Change current player
            if (this.game.getPlayers().get(playerNumber).getStatus() != PlayerStatus.DEFEATED) {
                this.game.setCurrentPlayer(this.game.getPlayers().get(playerNumber));
                return this;
            }

        }

    }

    public Object performAction(Action action)  {

        // Determine action type
        switch (action.getActionType()) {

            case ROLL_DICE -> {

                // Get random roll amount
                Random random = new Random();
                int rollAmount = random.nextInt(7, 10);

                // Print roll message
                System.out.print(this.getName() + " rolled a  ");

                // Initiate roll
                int currentRoll = 0;
                int diceOne = 0, diceTwo = 0;

                // Roll dice
                for (int i = 1; i <= rollAmount; i++) {

                    // Get current roll
                    diceOne = random.nextInt(1, 7);
                    diceTwo = random.nextInt(1, 7);
                    int roll = diceOne + diceTwo;

                    // Print current roll
                    System.out.print("\b".repeat(String.valueOf(currentRoll).length()) + ConsoleColors.GREEN + roll + ConsoleColors.RESET);
                    currentRoll = roll;
                    try {
                        Thread.sleep(50 + i*30L);
                    } catch ( InterruptedException ignored ) {}

                    // Return roll if it's the last roll
                    if (i == rollAmount) {
                        System.out.print("\n");
                    }

                }

                if (this.getName().equals("a")) {
                    currentRoll = 30;
                    diceOne = 15;
                    diceTwo = 15;
                }

                // Game is running
                if (this.getGame().isRunning()) {

                    // Player isn't in jail
                    if (!this.isInJail()) {

                        // Check if player rolls doubles
                        if (diceOne == diceTwo) {
                            this.increaseTimesRolledDoubles();
                            this.setRolledDoubles(true);
                        } else {
                            this.setRolledDoubles(false);
                        }

                        // End turn immediately if third doubles roll
                        if (this.getTimesRolledDoubles() == 3) {
                            this.performAction(new Action(ActionType.GO_TO_JAIL));
                        }

                        // Move spaces forward
                        else {
                            this.moveSpaces(currentRoll);
                        }

                    }

                    // Player is in jail
                    else {

                        if (diceOne == diceTwo) {
                            this.setInJail(false);
                        }

                        else {
                            this.increaseTurnsInJail();
                            if (this.getTurnsInJail() == 3) {
                                this.setForcedAction(new Action(ActionType.PAY_JAIL_FINE));
                            }
                        }

                    }

                }

                return currentRoll;

            }

            case DECLARE_BANKRUPTCY -> {

                // Reset stats
                this.setStatus(PlayerStatus.DEFEATED);
                this.decreaseBalance(this.balance);
                this.moveToSpace(0);
                this.properties.clear();
                this.endTurn();

                // Determine winner if there is one
                if (this.getGame().getActivePlayers().size() == 1) {
                    this.getGame().setWinner(this.getGame().getActivePlayers().get(0));
                }

            }

            case CONFIRM -> {

            }

            case CONFIRM_CARD_ACTION -> {

                ArrayList<Object> card = this.game.getLastDrawnCard();
                Action cardAction = new Action((ActionType) card.get(1));

                switch(cardAction.getActionType()) {
                    case RECEIVE_MONEY -> {
                        this.increaseBalance((int) card.get(2));
                    }
                }

                this.setForcedAction(null);

            }

            case PAY_TAXES -> {
                int tax = ((Tax) this.getGame().getSpaces().get(this.getSpace())).getTax();
                this.decreaseBalance(tax);
            }

            case DRAW_CARD -> {
                switch (this.getGame().getSpaces().get(this.getSpace()).getSpaceType()) {
                    case CHANCE -> {
                        ArrayList<Object> card = this.getGame().drawCard(this.getGame().getChanceCards());
                        System.out.println(card.get(0));
                        this.setForcedAction(new Action(ActionType.CONFIRM_CARD_ACTION));
                    }
                    case COMMUNITY_CHEST -> {
                        ArrayList<Object> card = this.getGame().drawCard(this.getGame().getCommunityChestCards());
                        System.out.println(card.get(0));
                        this.setForcedAction(new Action(ActionType.CONFIRM_CARD_ACTION));
                    }
                }
            }

            case GO_TO_JAIL -> {
                this.setInJail(true);
                this.moveToSpace(10);
                this.endTurn();
            }

            case PAY_JAIL_FINE -> {
                this.setInJail(false);
                this.decreaseBalance(50);
                this.moveToSpace(10);
            }

        }

        System.out.println(this.getStatus().toString());

        // Return Player
        return this;

    }

    /** @Info Checks whether the player can perform a certain action */
    public boolean canPerformAction(Action action) {

        // Player is not in jail
        if (!this.isInJail()) {

            switch ( action.getActionType() ) {

                case ROLL_DICE -> {
                    return this.status == PlayerStatus.TURN_START || this.isInJail();
                }

                case DECLARE_BANKRUPTCY -> {
                    return true;
                }

                // TURN_MOVED
                case PAY_TAXES -> {
                    return (this.getGame().getSpaces().get(this.getSpace()) instanceof Tax) && this.getBalance() >= ((Tax) this.getGame().getSpaces().get(this.getSpace())).getTax();
                }

            /*case BUY_PROPERTY -> {

            // Request property position
            //System.out.print("Which set does your property belong to (1-" + getSets().size() + ")? ");
            //int property_set = Integer.parseInt(scanner.nextLine());
            //System.out.print("Which street in the set does your property belong to (1-" + getSets().get(property_set - 1).getProperties().size() + ")? ");
            //int property_street = Integer.parseInt(scanner.nextLine());

            // Purchase property
            //Property property = getSets().get(property_set - 1).getProperties().get(property_street - 1);
            //Action.buyProperty(player, property);

            }*/

                //case PAY_JAIL_BAIL -> {}

            }

        }

        // Player is in jail
        else {

            switch (action.getActionType()) {

                case ROLL_DICE, DECLARE_BANKRUPTCY -> {
                    return true;
                }

                case PAY_JAIL_FINE -> {
                    return this.balance > 50;
                }

            }

        }

        return false;

    }

    public ArrayList<ActionType> getPossibleActionTypes() {

        // Initiate action types
        ArrayList<ActionType> actionTypes = new ArrayList<>();

        // Player has a forced action
        if (this.hasForcedAction()) {
            return new ArrayList<>(){{
                add(getForcedAction().getActionType());
                add(ActionType.DECLARE_BANKRUPTCY);
            }};
        }

        // Add action based on landed space
        if (this.getStatus() == PlayerStatus.TURN_MOVED) {
            Space space = this.getGame().getSpaces().get(this.getSpace());
            switch (space.getSpaceType()) {
                case CHANCE, COMMUNITY_CHEST -> {
                    actionTypes.add(ActionType.DRAW_CARD);
                }
                case GO_TO_JAIL -> {
                    System.out.println("You are forced to go to jail.");
                    actionTypes.add(ActionType.GO_TO_JAIL);
                }
            }
        }

        // Add possible action types
        for (ActionType actionType : Arrays.stream(ActionType.values()).toList()) {
            if ( this.canPerformAction(new Action(actionType)) && !actionTypes.contains(actionType) ) {
                actionTypes.add(actionType);
            }
        }

        // Add confirm if the player can only declare bankruptcy
        if (actionTypes.size() == 1 && actionTypes.get(0) == ActionType.DECLARE_BANKRUPTCY) {
            actionTypes.add(ActionType.CONFIRM);
        }

        // Return possible action types
        return actionTypes;

    }

}
